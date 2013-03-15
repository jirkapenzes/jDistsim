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
import jDistsim.core.simulation.simulator.SimulatorOutput;
import jDistsim.core.simulation.simulator.entity.Entity;
import jDistsim.utils.common.Counter;
import jDistsim.utils.logging.Logger;

import java.rmi.RemoteException;
import java.util.HashMap;

/**
 * Author: Jirka Pénzeš
 * Date: 19.2.13
 * Time: 0:26
 */
public class DistributedSimulator extends BaseSimulator {

    private HashMap<String, ModelContainer> models;
    private HashMap<String, DistributedReceiveModule> receiverModules;

    private boolean executeCondition;
    private Communication communication;
    private boolean isDistributed;
    private double minimalLookahead = 0;
    private LocalNetworkSettings networkSettings;
    private boolean ready = false;

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
                models.put(distributedSenderModule.getDistributedModelDefinition().getRmiModelName(), new ModelContainer(distributedSenderModule.getDistributedModelDefinition()));
            }

            // calculate minimal minimalLookahead time
            if (module instanceof ITimeAffectModule) {
                ITimeAffectModule timeAffectModule = (ITimeAffectModule) module;
                if (timeAffectModule.getMinimalAffectTime() < minimalLookahead || minimalLookahead == 0) {
                    minimalLookahead = timeAffectModule.getMinimalAffectTime();
                }
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
            DistributedModule distributedModule = (DistributedModule) module;
            String modelName = distributedModule.getDistributedModelDefinition().getRmiModelName();

            if (!models.containsKey(modelName))
                throw new CommunicationException();

            ModelContainer container = models.get(modelName);
            container.getCounter().decrement();

            if (container.getModelDefinition().isLookahead()) {
                if (container.getCounter().getCurrentValue() == 0) {
                    checkLookahead(container);
                }
            }
            checkExecuteCondition();
        }
    }

    private void checkExecuteCondition() {
        for (ModelContainer modelContainer : models.values()) {
            if (modelContainer.getCounter().getCurrentValue() == 0) {
                executeCondition = false;
                return;
            }
        }
        executeCondition = true;
    }

    private void checkLookahead(ModelContainer modelContainer) {
        try {
            double lookahead = modelContainer.getRemote().getLookahead();
            plan(getLocalTime() + lookahead, new NullModule(modelContainer.getModelDefinition()), null);
            modelContainer.getCounter().increment();
        } catch (RemoteException e) {
            Logger.log(e);
            throw new DistributedException(e.getMessage());
        } finally {
            communication.stop();
        }
    }

    @Override
    protected boolean canExecute() {
        return isDistributed ? executeCondition : true;
    }

    @Override
    protected void prepare(ISimulationModel simulationModel) {
        try {
            isDistributed = isDistributed(simulationModel);
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
                    receiverModules.put(receiveModule.getAuthorizedEntityName(), receiveModule);
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
                    // throw new DistributedException("Model " + remoteObjectName + " not authorized");
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
    protected void initializeSimulator(ISimulationModel simulationModel) {
        super.initializeSimulator(simulationModel);
        if (!isDistributed) return;

        getOutput().sendToOutput(SimulatorOutput.MessageType.Standard, "Initialize minimalLookahead messages");
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
        getOutput().sendToOutput(SimulatorOutput.MessageType.Standard, "Receive new entity");
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
        receiveModule.setDistributedModelDefinition(models.get(modelName).getModelDefinition());
        plan(time, receiveModule, entity);
        checkExecuteCondition();
    }

    public double getLookahead() {
        return minimalLookahead;
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
