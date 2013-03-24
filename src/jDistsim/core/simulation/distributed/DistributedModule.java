package jDistsim.core.simulation.distributed;

import jDistsim.core.simulation.modules.Module;
import jDistsim.core.simulation.simulator.ISimulator;
import jDistsim.core.simulation.simulator.entity.Entity;

/**
 * Author: Jirka Pénzeš
 * Date: 1.3.13
 * Time: 20:43
 */
public abstract class DistributedModule<Settings extends DistributedModuleSettings> extends Module<Settings > {

    public DistributedModule(Settings disSettings) {
        super(disSettings);
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
}
