package jDistsim.core.simulation.simulator;

import jDistsim.core.simulation.animation.ISimulationAnimator;
import jDistsim.core.simulation.exception.EventNotFoundException;
import jDistsim.core.simulation.exception.ModelNotValidException;
import jDistsim.core.simulation.exception.SimulatorCoreException;
import jDistsim.core.simulation.exception.TimeNotSynchronizedException;
import jDistsim.core.simulation.model.ISimulationModelValidator;
import jDistsim.core.simulation.modules.Module;
import jDistsim.core.simulation.modules.RootModule;
import jDistsim.core.simulation.simulator.entity.Entity;
import jDistsim.core.simulation.simulator.event.Calendar;
import jDistsim.core.simulation.simulator.event.EventContainer;
import jDistsim.core.simulation.simulator.event.ScheduleEvent;
import jDistsim.core.simulation.validator.ValidatorException;
import jDistsim.core.simulation.validator.ValidatorResult;
import jDistsim.utils.common.ThreadWaiter;
import jDistsim.utils.persistence.AsyncWorker;

import java.util.Date;

/**
 * Author: Jirka Pénzeš
 * Date: 18.2.13
 * Time: 10:38
 */
public abstract class BaseSimulator implements ISimulator {

    protected Calendar calendar;
    private double localTime;
    private boolean run;
    private SimulatorEnvironment environment;
    private SimulatorOutput output;
    private ISimulatorEndCondition endCondition;
    private ISimulationModelValidator modelValidator;
    private ISimulationAnimator animator;
    private AsyncWorker asyncWorker;
    protected ISimulationModel currentSimulationModel;
    protected volatile Object lock = new Object();

    public BaseSimulator(ISimulationModelValidator modelValidator) {
        this(modelValidator, new ISimulatorEndCondition() {
            @Override
            public boolean occurred(SimulatorEnvironment environment) {
                return false;
            }
        });
    }

    public BaseSimulator(ISimulationModelValidator modelValidator, ISimulatorEndCondition endCondition) {
        this(modelValidator, 0, endCondition);
    }

    public BaseSimulator(ISimulationModelValidator modelValidator, double initialLocalTime, ISimulatorEndCondition endCondition) {
        this.localTime = initialLocalTime;
        this.endCondition = endCondition;
        this.modelValidator = modelValidator;

        environment = new SimulatorEnvironment();
        output = new SimulatorOutput();
        calendar = new Calendar();
        run = false;
    }

    @Override
    public SimulatorEnvironment getEnvironment() {
        return environment;
    }

    @Override
    public SimulatorOutput getOutput() {
        return output;
    }

    @Override
    public double getLocalTime() {
        return localTime;
    }

    @Override
    public void setAnimator(ISimulationAnimator animator) {
        this.animator = animator;
    }

    @Override
    public void plan(double time, Module module, Entity entity) {
        if (time < localTime)
            throw new TimeNotSynchronizedException(time, localTime);

        ScheduleEvent scheduleEvent = new ScheduleEvent(time, new EventContainer(module, entity));
        calendar.put(scheduleEvent);
    }

    protected abstract void classification(Module module);

    protected abstract boolean canExecute();

    protected abstract void unexpectedExit();

    @Override
    public void simulate(ISimulationModel simulationModel) throws SimulatorCoreException {
        currentSimulationModel = simulationModel;
        try {
            asyncWorker = new AsyncWorker() {
                @Override
                public void doWork() {
                    prepareOutput();
                    prepareEnvironment();
                    showSimulatorInfo();
                    validateModel(currentSimulationModel);

                    prepare(currentSimulationModel);
                    initializeModules(currentSimulationModel);
                    initializeSimulator(currentSimulationModel);

                    output.drawSeparateLine();
                    if (currentSimulationModel == null)
                        return;
                }

                @Override
                public void workerCompleted() {
                    getOutput().sendToOutput(SimulatorOutput.MessageType.Standard, "Simulator initialization is complete");
                }

                @Override
                public void rollback() {
                    getOutput().sendToOutput(SimulatorOutput.MessageType.Standard, "Simulator rollback");
                    unexpectedExit();

                }
            };

            asyncWorker.runAsync().waitForComplete();
            output.sendToOutput(SimulatorOutput.MessageType.Standard, "Start simulation");
            run = true;
            while (run && !endCondition.occurred(environment)) {
                while (run && (calendar.isEmpty() || !canExecute())) {
                    ThreadWaiter.waitCurrentThreadFor(20);
                }
                if (!run) break;

                ScheduleEvent scheduleEvent = calendar.peek();
                if (scheduleEvent == null)
                    throw new EventNotFoundException();

                Module module = scheduleEvent.getEventContainer().getModule();
                Entity entity = scheduleEvent.getEventContainer().getEntity();

                synchronized (lock) {
                    setEntityState(entity, module);
                    animate(entity);
                    localTime = scheduleEvent.getTime();
                    drawCurrentPrecessInfo(module, entity);
                    module.execute(this, entity);
                }

                classification(module);
                calendar.remove(scheduleEvent);
                updateEnvironment();
            }
        } catch (Exception exception) {
            output.drawSeparateLine();
            output.sendToOutput(SimulatorOutput.MessageType.Error, "Error -> " + exception.getClass().getSimpleName());
            output.sendToOutput(SimulatorOutput.MessageType.Error, "Unexpected end of simulation");
            throw new SimulatorCoreException(exception);
        } finally {
            dispose();
        }
        output.drawSeparateLine();
        output.sendToOutput(SimulatorOutput.MessageType.Standard, "End of simulation");
    }

    private void prepareEnvironment() {
        output.sendToOutput(SimulatorOutput.MessageType.Standard, "Prepare environment");
        getEnvironment().clear();
        output.sendToOutput(SimulatorOutput.MessageType.Standard, "Initialize environment");
        getEnvironment().setSimulatorAtt("simulator", getLocalTime());
        getEnvironment().setDistributedAtt("distributed", "true");
        getEnvironment().setNetworkAtt("network", "true");
        getEnvironment().setModulesAtt("modules", "true");
        getEnvironment().notifyObservers();
    }

    protected abstract void prepare(ISimulationModel simulationModel);

    private void animate(Entity entity) {
        if (entity == null) return;
        String moduleFrom = entity.getAttributes().get("previousModule").getValue();
        String moduleTo = entity.getAttributes().get("currentModule").getValue();
        String iconName = entity.getAttributes().get("iconName").getValue();
        animator.animate(moduleFrom, moduleTo, iconName);
    }

    private void drawCurrentPrecessInfo(Module module, Entity entity) {
        String entityName = "";
        if (entity != null)
            entityName = " {entity: " + entity.getShortIdentifier() + "}";
        output.sendToOutput(SimulatorOutput.MessageType.Standard, "Local time: " + getLocalTime() + " Process module " + module.getIdentifier() + entityName + "; ");
    }

    protected abstract void fillEnvironment();

    private void updateEnvironment() {
        fillEnvironment();

        getEnvironment().setSimulatorAtt("local-time", getLocalTime());
        getEnvironment().notifyObservers();
    }

    private void showSimulatorInfo() {
        output.sendToOutput(SimulatorOutput.MessageType.Standard, "Application jDistSim 0.7 by Jirka Penzes");
        output.sendToOutput(SimulatorOutput.MessageType.Standard, "Simulator started on " + new Date());
        output.drawSeparateLine();
        output.sendToOutput(SimulatorOutput.MessageType.Standard, "Preparing simulator ... ");
    }

    private void prepareOutput() {
        output.clearOutput();
    }

    @Override
    public void stop() {
        run = false;

        if (asyncWorker != null && asyncWorker.isRun())
            asyncWorker.stop();

        output.drawSeparateLine();
        output.sendToOutput(SimulatorOutput.MessageType.Standard, "End of simulation -> explicit stop");
    }

    protected void validateModel(ISimulationModel simulationModel) {
        output.sendToOutput(SimulatorOutput.MessageType.Standard, "Validate model");
        ValidatorResult validatorResult = modelValidator.validateModel(simulationModel);

        if (validatorResult.isValid()) {
            output.sendToOutput(SimulatorOutput.MessageType.Standard, "Model is valid");
            return;
        }

        for (ValidatorException exception : validatorResult.getValidatorExceptions()) {
            output.sendToOutput(SimulatorOutput.MessageType.Error, "Validator error -> " + exception.getOriginator() + " -> " + exception.getMessage());
        }

        throw new ModelNotValidException(validatorResult);
    }

    protected void initializeSimulator(ISimulationModel simulationModel) {
        output.sendToOutput(SimulatorOutput.MessageType.Standard, "Initialize simulator");

        getEnvironment().setModelName(simulationModel.getModelName());
        for (RootModule rootModule : simulationModel.getRootModules()) {
            plan(rootModule.getSettings().getFirsCreation(), rootModule, null);
        }
    }

    private void initializeModules(ISimulationModel simulationModel) {
        output.sendToOutput(SimulatorOutput.MessageType.Standard, "Initialize modules");
        for (Module module : simulationModel.getModules()) {
            output.sendToOutput(SimulatorOutput.MessageType.Standard, "Initialize -> " + module.getIdentifier());
            module.initializeForSimulation(this);
        }
    }

    public void setEntityState(Entity entity, Module module) {
        if (entity == null) return;
        String previousModule = entity.getAttributes().get("currentModule").getValue();
        entity.getAttributes().put("previousModule", previousModule);
        entity.getAttributes().put("currentModule", module.getIdentifier());
        // output.sendToOutput(entity.getAttributes().get("previousModule").getValue() + " -> " + entity.getAttributes().get("currentModule").getValue());
    }
}
