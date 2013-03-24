package jDistsim.core.simulation.modules;

import jDistsim.core.simulation.simulator.ISimulator;
import jDistsim.core.simulation.simulator.entity.Entity;

/**
 * Author: Jirka Pénzeš
 * Date: 21.2.13
 * Time: 22:35
 */
public abstract class RootModule extends Module<RootSettings> {

    public RootModule(RootSettings rootSettings) {
        super(rootSettings);
        createdModule = true;
    }

    public abstract void logic(ISimulator simulator);

    @Override
    public void logic(ISimulator simulator, Entity entity) {
        logic(simulator);
    }

    @Override
    protected void preExecute(ISimulator simulator, Entity entity) {
    }
}
