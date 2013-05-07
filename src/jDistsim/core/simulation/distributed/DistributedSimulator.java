package jDistsim.core.simulation.distributed;

import jDistsim.application.designer.common.Application;
import jDistsim.application.designer.common.LocalNetworkSettings;
import jDistsim.core.simulation.distributed.communication.Communication;
import jDistsim.core.simulation.distributed.communication.IRemote;
import jDistsim.core.simulation.exception.CommunicationException;
import jDistsim.core.simulation.exception.DistributedException;
import jDistsim.core.simulation.model.ISimulationModelValidator;
import jDistsim.core.simulation.modules.ITimeAffectModule;
import jDistsim.core.simulation.modules.Module;
import jDistsim.core.simulation.modules.lib.NullModule;
import jDistsim.core.simulation.simulator.BaseSimulator;
import jDistsim.core.simulation.simulator.ISimulationModel;
import jDistsim.core.simulation.simulator.ISimulator;
import jDistsim.core.simulation.simulator.SimulatorOutput;
import jDistsim.core.simulation.simulator.entity.Entity;
import jDistsim.core.simulation.simulator.event.ScheduleEvent;
import jDistsim.utils.common.Counter;
import jDistsim.utils.logging.Logger;

import java.rmi.RemoteException;
import java.util.HashMap;

/**
 * Author: Jirka Pénzeš
 * Date: 19.2.13
 * Time: 0:26
 */
public class DistributedSimulator extends BaseSimulator implements ISimulator {

    private HashMap<String, ModelContainer> models;
    private HashMap<String, DistributedReceiveModule> receiverModules;

    private volatile boolean executeCondition;
    private Communication communication;
    private boolean isDistributed;
    private double minimalLookahead = 0;
    private double minimalModelTime = 0;
    private LocalNetworkSettings networkSettings;
    private boolean ready = false;
    private double safeTime;

    public DistributedSimulator(ISimulationModelValidator modelValidator, LocalNetworkSettings networkSettings) {
        super(modelValidator);
        this.networkSettings = networkSettings;
        communication = new Communication(networkSettings);
        models = new HashMap<>();
        receiverModules = new HashMap<>();
    }

    private void assortModules(ISimulationModel simulationModel) {
        for (Module module : simulationModel.getModules()) {
            if (module instanceof DistributedSenderModule) {
                DistributedSenderModule distributedSenderModule = (DistributedSenderModule) module;

                // TODO spíš použít long identifier pro distribuovaný modul
                models.put(distributedSenderModule.getSettings().getDistributedModelDefinition().getRmiModelName(), new ModelContainer(distributedSenderModule.getSettings().getDistributedModelDefinition()));
            }

            // calculate minimal minimalLookahead time
            if (module instanceof ITimeAffectModule) {
                ITimeAffectModule timeAffectModule = (ITimeAffectModule) module;
                if (timeAffectModule.getMinimalAffectTime() < minimalLookahead || minimalLookahead == 0) {
                    minimalLookahead = timeAffectModule.getMinimalAffectTime();
                }
                minimalModelTime += timeAffectModule.getMinimalAffectTime();
            }

        }
        // TODO netahat to z jádra aplikace
        for (DistributedModelDefinition modelDefinition : Application.global().getDistributedModels().values()) {
            if (modelDefinition.isReceive()) {
                if (!models.containsKey(modelDefinition.getRmiModelName()))
                    models.put(modelDefinition.getRmiModelName(), new ModelContainer(modelDefinition));
            }
        }
    }

    private boolean isDistributed(ISimulationModel simulationModel) {
        getOutput().sendToOutput(SimulatorOutput.MessageType.Standard, "Check for distributed modules");
        isDistributed = false;
        for (Module module : simulationModel.getModules()) {
            if (module instanceof DistributedModule) {
                return true;
            }
        }
        return false;
    }

    @Override
    protected void classification(Module module) {
        if (module instanceof DistributedReceiveModule || module instanceof NullModule) {
            DistributedModule<DistributedModuleSettings> distributedModule = (DistributedModule) module;
            String modelName = distributedModule.getSettings().getDistributedModelDefinition().getRmiModelName();

            if (!models.containsKey(modelName))
                throw new CommunicationException();

            ModelContainer container = models.get(modelName);
            container.getCounter().decrement();

            if (container.getModelDefinition().isLookahead()) {
                if (container.getCounter().getCurrentValue() == 0) {
                    if (container.getModelDefinition().isLookahead())
                        checkLookahead(container);
                }
            }
            checkExecuteCondition();
            getOutput().sendToOutput(SimulatorOutput.MessageType.Standard, "-> counter: " + container.getCounter().getCurrentValue());
        }
    }

    private void checkExecuteCondition() {
        for (ModelContainer modelContainer : models.values()) {
            if (modelContainer.getCounter().getCurrentValue() == 0) {
                executeCondition = false;
                Logger.log("Execution ->  " + "false");
                return;
            }
        }
        Logger.log("Execution ->  " + "true");
        executeCondition = true;
    }

    @Override
    protected void prepare(ISimulationModel simulationModel) {
        try {
            isDistributed = isDistributed(simulationModel);
            safeTime = -1;

            if (isDistributed) {
                getOutput().sendToOutput(SimulatorOutput.MessageType.Standard, "Simulation model is distributed");
            } else {
                getOutput().sendToOutput(SimulatorOutput.MessageType.Standard, "Simulation model is not distributed");
                return;
            }
            getOutput().sendToOutput(SimulatorOutput.MessageType.Standard, "Find all distributed models in simulation model");
            assortModules(simulationModel);

            for (Module module : simulationModel.getModules()) {
                if (module instanceof DistributedReceiveModule) {
                    DistributedReceiveModule receiveModule = (DistributedReceiveModule) module;
                    receiverModules.put(receiveModule.getSettings().getAuthorizedEntityName(), receiveModule);
                }
            }

            getOutput().sendToOutput(SimulatorOutput.MessageType.Standard, "Found " + models.size() + " dependency distributed models");
            getOutput().sendToOutput(SimulatorOutput.MessageType.Standard, "Register local network model");
            communication.start(this);
            getOutput().sendToOutput(SimulatorOutput.MessageType.Standard, "Find distributed remote models");

            for (ModelContainer modelContainer : models.values()) {
                getOutput().sendToOutput(SimulatorOutput.MessageType.Standard, "Wait for " + modelContainer);
                IRemote remote = communication.bind(modelContainer.getModelDefinition());
                modelContainer.setRemote(remote);
                getOutput().sendToOutput(SimulatorOutput.MessageType.Standard, "Remote model " + modelContainer + " has been found");

                boolean authorize = remote.authorize(networkSettings.getModelName());
                if (!authorize) {
                    throw new DistributedException("Model " + modelContainer.getModelDefinition().getRmiModelName() + " not authorized");
                }
            }
            ready = true;
            getOutput().sendToOutput(SimulatorOutput.MessageType.Standard, "Wait for ready all models");
            for (ModelContainer modelContainer : models.values()) {
                getOutput().sendToOutput(SimulatorOutput.MessageType.Standard, "Wait for ready -> " + modelContainer.getModelDefinition().getRmiModelName());
                modelContainer.getRemote().waitForReady();
            }
            getOutput().sendToOutput(SimulatorOutput.MessageType.Standard, "Prepare method done");
        } catch (Exception exception) {
            getOutput().sendToOutput(SimulatorOutput.MessageType.Error, exception.getMessage());
            throw new DistributedException(exception.getMessage());
        } finally {
            // communication.stop();
        }
    }

    @Override
    protected void fillEnvironment() {
        getEnvironment().setSimulatorAtt("safe-time", safeTime);
    }

    @Override
    protected void initializeSimulator(ISimulationModel simulationModel) {
        super.initializeSimulator(simulationModel);
        if (!isDistributed) return;

        getOutput().sendToOutput(SimulatorOutput.MessageType.Standard, "Initialize distributed lookahead");
        if (simulationModel.getNumberOfRootModules() == 0)
            return;

        getOutput().sendToOutput(SimulatorOutput.MessageType.Standard, "Getting lookahead messages");
        for (ModelContainer modelContainer : models.values()) {
            if (modelContainer.getModelDefinition().isLookahead()) {
                checkLookahead(modelContainer);
            }
        }
        checkExecuteCondition();
    }

    @Override
    protected void validateModel(ISimulationModel simulationModel) {
        super.validateModel(simulationModel);
    }

    public void addDistributeModule(double time, Entity entity, String modelName) {
        getOutput().sendToOutput(SimulatorOutput.MessageType.Standard, "Receive new entity ");
        String authorizedEntityName = entity.getAttributes().get("d.entity").getValue();
        if (!models.containsKey(modelName)) {
            getOutput().sendToOutput(SimulatorOutput.MessageType.Warning, "Unknown model name");
            return;
        }
        if (!receiverModules.containsKey(authorizedEntityName)) {
            getOutput().sendToOutput(SimulatorOutput.MessageType.Warning, "Unknown entity authorized name");
            return;
        }
        DistributedReceiveModule receiveModule = receiverModules.get(authorizedEntityName).clone();
        models.get(modelName).getCounter().increment();
        receiveModule.getSettings().setDistributedModelDefinition(models.get(modelName).getModelDefinition());
        getOutput().sendToOutput(SimulatorOutput.MessageType.Standard, "Descriptor -> " + entity.getIdentifier() + " at time " + time);
        // removeAllNullMessages(modelName);
        plan(time, receiveModule, entity);
        safeTime = time;
        checkExecuteCondition();
    }

    @Override
    protected boolean canExecute() {
        return isDistributed ? executeCondition || calendar.peek().getTime() <= safeTime : true;
    }

    private void checkLookahead(ModelContainer modelContainer) {
        try {
            getOutput().sendToOutput(SimulatorOutput.MessageType.Standard, "Send lookahead");
            modelContainer.getRemote().getLookahead(getLocalTime(), communication.getNetworkSettings().getModelName());
        } catch (Exception e) {
            Logger.log(e);
            throw new DistributedException(e.getMessage());
        } finally {
            communication.stop();
        }
    }

    public void planNullModule(double time, String requester) {
        ModelContainer modelContainer = models.get(requester);

        safeTime = time;
        modelContainer.getCounter().increment();
        NullModule module = new NullModule(modelContainer.getModelDefinition());
        plan(time, module, null);
        checkExecuteCondition();
    }

    public void getLookahead(double requesterTime, String requester) {
        synchronized (lock) {
            Logger.log("Lookhead request");
            try {
                ModelContainer modelContainer = models.get(requester);
                if (calendar.isEmpty()) {
                    modelContainer.getRemote().processNullModule(requesterTime + minimalLookahead, communication.getNetworkSettings().getModelName());
                    Logger.log("Immediately response -> requester time: " + requesterTime + " add lookahead " + minimalLookahead);
                    return;
                }

                double lbts = getLocalTime() + minimalLookahead;
                ScheduleEvent immediateScheduleSender = findImmediateSender();
                if (immediateScheduleSender != null) {
                    if (lbts > immediateScheduleSender.getTime()) {
                        lbts = immediateScheduleSender.getTime();
                    }
                }
                if (requesterTime > lbts) {
                    Logger.log("Local response -> requester time: " + requesterTime);
                    plan(requesterTime, new NullModuleSender(modelContainer.getModelDefinition()), null);
                    return;
                }
                Logger.log("Remote response -> requester time: " + requesterTime + " LBTS -> " + lbts);
                modelContainer.getRemote().processNullModule(lbts, communication.getNetworkSettings().getModelName());
            } catch (RemoteException e) {
                System.out.println("error");
            }
        }
    }

    private ScheduleEvent findImmediateSender() {
        for (ScheduleEvent scheduleEvent : calendar) {
            Module module = scheduleEvent.getEventContainer().getModule();
            if (module instanceof DistributedSenderModule) {
                return scheduleEvent;
            }
        }
        return null;
    }

    public IRemote getRemote(String rmiModelName) {
        return models.get(rmiModelName).getRemote();
    }

    public LocalNetworkSettings getNetwork() {
        return networkSettings;
    }

    public boolean isReady() {
        return ready;
    }

    @Override
    public void dispose() {
        Logger.log("Stop communication");
        communication.stop();
    }

    private class ModelContainer {
        private Counter counter;
        private DistributedModelDefinition modelDefinition;
        private IRemote remote;

        private ModelContainer(DistributedModelDefinition modelDefinition) {
            this.counter = new Counter();
            this.modelDefinition = modelDefinition;
        }

        public Counter getCounter() {
            return counter;
        }

        public DistributedModelDefinition getModelDefinition() {
            return modelDefinition;
        }

        public IRemote getRemote() {
            return remote;
        }

        public void setRemote(IRemote remote) {
            this.remote = remote;
        }

        @Override
        public String toString() {
            return modelDefinition.getRmiModelName();
        }
    }
}
