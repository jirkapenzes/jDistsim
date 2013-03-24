package jDistsim.core.simulation.modules.lib.sender;

import jDistsim.core.simulation.distributed.DistributedModelDefinition;
import jDistsim.core.simulation.distributed.DistributedSenderModule;
import jDistsim.core.simulation.distributed.DistributedSimulator;
import jDistsim.core.simulation.distributed.SenderSettings;
import jDistsim.core.simulation.distributed.communication.IRemote;
import jDistsim.core.simulation.exception.DistributedException;
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

    public Sender(SenderSettings senderSettings) {
        super(senderSettings);
    }

    @Override
    protected void initializeDefaultValues() {
        settings.setDistributedModelDefinition(DistributedModelDefinition.createNull());
        settings.setDistributedEntityKeyName("entity");
    }

    @Override
    protected void resetStates(DistributedSimulator simulator) {
    }

    @Override
    protected void logic(DistributedSimulator simulator, Entity entity) {
        try {
            entity.getAttributes().set(new Attribute("d.entity", settings.getDistributedEntityKeyName()));
            IRemote remote = simulator.getRemote(settings.getDistributedModelDefinition().getRmiModelName());
            remote.process(simulator.getLocalTime(), entity, simulator.getNetwork().getModelName());
        } catch (Exception exception) {
            simulator.getOutput().sendToOutput(SimulatorOutput.MessageType.Error, "Error sending");
            throw new DistributedException(exception.getMessage());
        }
    }

    @Override
    public boolean isValid() {
        boolean valid = super.isValid();
        if (settings.getDistributedModelDefinition().getRmiModelName().equals("null")) {
            return false;
        }
        if (settings.getDistributedModelDefinition().getPort() <= 0 || settings.getDistributedModelDefinition().getPort() > 65535) {
            return false;
        }
        return valid;
    }

    @Override
    protected void setChildProperty() {
        getProperties().set(new ModuleProperty("d.address", settings.getDistributedModelDefinition().getAddress(), "d.address"));
        getProperties().set(new ModuleProperty("d.rmiModelName", settings.getDistributedModelDefinition().getRmiModelName(), "d.rmi-name"));
        getProperties().set(new ModuleProperty("d.port", settings.getDistributedModelDefinition().getPort(), "d.port"));
        getProperties().set(new ModuleProperty("d.modelName", settings.getDistributedModelDefinition().getModelName(), "d.name"));
        getProperties().set(new ModuleProperty("d.lookahead", settings.getDistributedModelDefinition().isLookahead(), "d.lookahead"));
        getProperties().set(new ModuleProperty("d.entity", settings.getDistributedModelDefinition(), "d.lookahead"));
    }
}
