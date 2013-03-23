package jDistsim.core.simulation.distributed;

import jDistsim.core.simulation.distributed.communication.IRemote;
import jDistsim.core.simulation.exception.DistributedException;
import jDistsim.core.simulation.modules.ModuleConfiguration;
import jDistsim.core.simulation.simulator.ISimulator;
import jDistsim.core.simulation.simulator.SimulatorOutput;
import jDistsim.core.simulation.simulator.entity.Entity;

/**
 * Author: Jirka Pénzeš
 * Date: 23.3.13
 * Time: 1:10
 */
public class NullModuleSender extends DistributedModule {

    protected DistributedModelDefinition distributedModelDefinition;

    public NullModuleSender(DistributedModelDefinition modelDefinition) {
        super(null, new ModuleConfiguration("null_module_sender", null));
        this.distributedModelDefinition = modelDefinition;
    }

    @Override
    protected void logic(DistributedSimulator simulator, Entity entity) {
        try {
            IRemote remote = simulator.getRemote(distributedModelDefinition.getRmiModelName());
            remote.processNullModule(simulator.getLocalTime(), simulator.getNetwork().getModelName());
        } catch (Exception exception) {
            simulator.getOutput().sendToOutput(SimulatorOutput.MessageType.Error, "Error sending");
            throw new DistributedException();
        }
    }

    @Override
    protected void initializeDefaultValues() {
    }

    @Override
    protected void resetStates(DistributedSimulator simulator) {
    }

    @Override
    protected void preExecute(ISimulator simulator, Entity entity) {
    }

    @Override
    protected void setChildProperty() {
    }

    @Override
    public boolean isValid() {
        return true;
    }
}
