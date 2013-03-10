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

    private HashMap<String, ReceiverContainer> receivers;
    private HashMap<String, SenderContainer> senders;
    private HashMap<String, DistributedReceiveModule> receiverModules;

    private boolean executeCondition;
    private Communication communication;
    private boolean isDistributed;
    private double lookahead = 0;
    private LocalNetworkSettings networkSettings;

    public DistributedSimulator(ISimulationModelValidator modelValidator, LocalNetworkSettings networkSettings) {
        super(modelValidator);
        this.networkSettings = networkSettings;
        communication = new Communication(networkSettings);
        receivers = new HashMap<>();
        senders = new HashMap<>();
        receiverModules = new HashMap<>();
    }

    private void assortModules(ISimulationModel simulationModel) {
        for (Module module : simulationModel.getModules()) {
            if (module instanceof DistributedSenderModule) {
                DistributedSenderModule distributedSenderModule = (DistributedSenderModule) module;

                // TODO spíš použít long identifier pro distribuovaný modul
                senders.put(distributedSenderModule.getDistributedModelDefinition().getRmiModelName(), new SenderContainer(distributedSenderModule.getDistributedModelDefinition()));
            }

            // calculate minimal lookahead time
            if (module instanceof ITimeAffectModule) {
                ITimeAffectModule timeAffectModule = (ITimeAffectModule) module;
                if (timeAffectModule.getMinimalAffectTime() < lookahead || lookahead == 0) {
                    lookahead = timeAffectModule.getMinimalAffectTime();
                }
            }
        }
        // TODO netahat to z jádra aplikace
        for (DistributedModelDefinition modelDefinition : Application.global().getDistributedModels().values()) {
            if (modelDefinition.isReceive()) {
                receivers.put(modelDefinition.getRmiModelName(), new ReceiverContainer(modelDefinition));
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

            if (!receivers.containsKey(modelName))
                throw new CommunicationException();

            ReceiverContainer container = receivers.get(modelName);
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
        for (ReceiverContainer modelContainer : receivers.values()) {
            if (modelContainer.getCounter().getCurrentValue() == 0) {
                executeCondition = false;
                return;
            }
        }
        executeCondition = true;
    }

    private void checkLookahead(ReceiverContainer receiverContainer) {
        try {
            double lookahead = receiverContainer.getRemote().getLookahead();
            plan(getLocalTime() + lookahead, new NullModule(receiverContainer.getModelDefinition()), null);
            receiverContainer.getCounter().increment();
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

            getOutput().sendToOutput(SimulatorOutput.MessageType.Standard, "Found " + (receivers.size() + senders.size()) + " dependency distributed models");
            getOutput().sendToOutput(SimulatorOutput.MessageType.Standard, "Register local network model");
            communication.start(this);
            getOutput().sendToOutput(SimulatorOutput.MessageType.Standard, "Find distributed remote models");

            for (SenderContainer senderContainer : senders.values()) {
                getOutput().sendToOutput(SimulatorOutput.MessageType.Standard, "Wait for " + senderContainer);
                IRemote remote = communication.bind(senderContainer.getModelDefinition());
                senderContainer.setRemote(remote);
                getOutput().sendToOutput(SimulatorOutput.MessageType.Standard, "Remote model " + senderContainer + " has been found");

                boolean authorize = remote.authorize(networkSettings.getModelName());
                if (!authorize) {
                    // throw new DistributedException("Model " + remoteObjectName + " not authorized");
                }
            }

            for (ReceiverContainer receiverContainer : receivers.values()) {
                if (senders.containsKey(receiverContainer.getModelDefinition().getRmiModelName())) {
                    receiverContainer.setRemote(senders.get(receiverContainer.getModelDefinition().getRmiModelName()).getRemote());
                    continue;
                }
                getOutput().sendToOutput(SimulatorOutput.MessageType.Standard, "Wait for " + receiverContainer);
                IRemote remote = communication.bind(receiverContainer.getModelDefinition());
                receiverContainer.setRemote(remote);
                getOutput().sendToOutput(SimulatorOutput.MessageType.Standard, "Remote model " + receiverContainer + " has been found");

                boolean authorize = remote.authorize(networkSettings.getModelName());
                if (!authorize) {
                    // throw new DistributedException("Model " + remoteObjectName + " not authorized");
                }
            }

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

        getOutput().sendToOutput(SimulatorOutput.MessageType.Standard, "Initialize lookahead messages");

        for (ReceiverContainer receiverContainer : receivers.values()) {
            if (receiverContainer.getModelDefinition().isLookahead()) {
                checkLookahead(receiverContainer);
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
        if (!receivers.containsKey(modelName)) {
            getOutput().sendToOutput(SimulatorOutput.MessageType.Warning, "Unknown model name");
            return;
        }
        if (!receiverModules.containsKey(authorizedEntityName)) {
            getOutput().sendToOutput(SimulatorOutput.MessageType.Warning, "Unknown entity authorized name");
            return;
        }

        DistributedReceiveModule receiveModule = receiverModules.get(authorizedEntityName).clone();
        receivers.get(modelName).getCounter().increment();
        receiveModule.setDistributedModelDefinition(receivers.get(modelName).getModelDefinition());
        plan(time, receiveModule, entity);
        checkExecuteCondition();
    }

    public double getLookahead() {
        return lookahead;
    }

    public IRemote getRemote(String rmiModelName) {
        return senders.get(rmiModelName).getRemote();
    }


    public LocalNetworkSettings getNetwork() {
        return networkSettings;
    }

    private class ReceiverContainer {
        private Counter counter;
        private DistributedModelDefinition modelDefinition;
        private IRemote remote;

        private ReceiverContainer(DistributedModelDefinition modelDefinition) {
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

    private class SenderContainer {
        private DistributedModelDefinition modelDefinition;
        private IRemote remote;

        private SenderContainer(DistributedModelDefinition modelDefinition) {
            this.modelDefinition = modelDefinition;
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
