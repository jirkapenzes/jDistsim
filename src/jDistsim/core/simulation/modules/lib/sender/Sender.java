package jDistsim.core.simulation.modules.lib.sender;

import jDistsim.core.simulation.distributed.DistributedModelDefinition;
import jDistsim.core.simulation.distributed.DistributedSenderModule;
import jDistsim.core.simulation.distributed.DistributedSimulator;
import jDistsim.core.simulation.distributed.communication.IRemote;
import jDistsim.core.simulation.exception.DistributedException;
import jDistsim.core.simulation.modules.ModuleConfiguration;
import jDistsim.core.simulation.modules.common.ModuleProperty;
import jDistsim.core.simulation.simulator.SimulatorOutput;
import jDistsim.core.simulation.simulator.entity.Attribute;
import jDistsim.core.simulation.simulator.entity.Entity;

/**
 * Author: Jirka Pénzeš
 * Date: 1.3.13
 * Time: 20:27
 */
public class Sender extends DistributedSenderModule {

    public Sender(ModuleConfiguration moduleConfiguration) {
        super(moduleConfiguration);
    }

    @Override
    protected void initializeDefaultValues() {
        distributedModelDefinition = DistributedModelDefinition.createNull();
        distributedEntityKeyName = "entity";
    }

    @Override
    protected void resetStates(DistributedSimulator simulator) {
    }

    @Override
    protected void logic(DistributedSimulator simulator, Entity entity) {
        try {
            entity.getAttributes().set(new Attribute("d.entity", distributedEntityKeyName));
            IRemote remote = simulator.getRemote(distributedModelDefinition.getRmiModelName());
            remote.process(simulator.getLocalTime(), entity, simulator.getNetwork().getModelName());
        } catch (Exception exception) {
            simulator.getOutput().sendToOutput(SimulatorOutput.MessageType.Error, "Error sending");
            throw new DistributedException(exception.getMessage());
        }
    }

    @Override
    public boolean isValid() {
        boolean valid = super.isValid();
        if (getDistributedModelDefinition().getRmiModelName().equals("null")) {
            return false;
        }
        if (getDistributedModelDefinition().getPort() <= 0 || getDistributedModelDefinition().getPort() > 65535) {
            return false;
        }
        return valid;
    }

    @Override
    protected void setChildProperty() {
        getProperties().set(new ModuleProperty("d.address", distributedModelDefinition.getAddress(), "d.address"));
        getProperties().set(new ModuleProperty("d.rmiModelName", distributedModelDefinition.getRmiModelName(), "d.rmi-name"));
        getProperties().set(new ModuleProperty("d.port", distributedModelDefinition.getPort(), "d.port"));
        getProperties().set(new ModuleProperty("d.modelName", distributedModelDefinition.getModelName(), "d.name"));
        getProperties().set(new ModuleProperty("d.lookahead", distributedModelDefinition.isLookahead(), "d.lookahead"));
        getProperties().set(new ModuleProperty("d.entity", distributedEntityKeyName, "d.lookahead"));
    }
}
