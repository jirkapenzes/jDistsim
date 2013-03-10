package jDistsim.core.simulation.distributed;

import jDistsim.core.simulation.modules.Module;
import jDistsim.core.simulation.modules.ModuleConfiguration;
import jDistsim.core.simulation.simulator.ISimulator;
import jDistsim.core.simulation.simulator.entity.Entity;
import jDistsim.ui.module.ModuleView;

/**
 * Author: Jirka Pénzeš
 * Date: 1.3.13
 * Time: 20:43
 */
public abstract class DistributedModule extends Module {

    private DistributedModelDefinition distributedModelDefinition;

    public DistributedModule(ModuleView view, ModuleConfiguration moduleConfiguration) {
        super(view, moduleConfiguration);
    }

    @Override
    protected void resetStates(ISimulator simulator) {
        resetStates((DistributedSimulator) simulator);
    }

    @Override
    protected void logic(ISimulator simulator, Entity entity) {
        logic((DistributedSimulator) simulator, entity);
    }

    protected abstract void resetStates(DistributedSimulator simulator);

    protected abstract void logic(DistributedSimulator simulator, Entity entity);

    public DistributedModelDefinition getDistributedModelDefinition() {
        return distributedModelDefinition;
    }

    public void setDistributedModelDefinition(DistributedModelDefinition distributedModelDefinition) {
        this.distributedModelDefinition = distributedModelDefinition;
    }
}
