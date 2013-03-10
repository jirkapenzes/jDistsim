package jDistsim.core.simulation.modules.lib;

import jDistsim.core.simulation.distributed.DistributedModelDefinition;
import jDistsim.core.simulation.distributed.DistributedModule;
import jDistsim.core.simulation.distributed.DistributedSimulator;
import jDistsim.core.simulation.modules.ModuleConfiguration;
import jDistsim.core.simulation.simulator.ISimulator;
import jDistsim.core.simulation.simulator.entity.Entity;

/**
 * Author: Jirka Pénzeš
 * Date: 9.3.13
 * Time: 0:29
 */
public class NullModule extends DistributedModule {

    public NullModule(DistributedModelDefinition modelDefinition) {
        super(null, new ModuleConfiguration("null_module", null));
        setDistributedModelDefinition(modelDefinition);
    }

    @Override
    protected void initializeDefaultValues() {
    }

    @Override
    protected void resetStates(DistributedSimulator simulator) {
    }

    @Override
    protected void logic(DistributedSimulator simulator, Entity entity) {
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
