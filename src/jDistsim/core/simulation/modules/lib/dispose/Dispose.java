package jDistsim.core.simulation.modules.lib.dispose;

import jDistsim.core.simulation.modules.Module;
import jDistsim.core.simulation.simulator.ISimulator;
import jDistsim.core.simulation.simulator.entity.Entity;

/**
 * Author: Jirka Pénzeš
 * Date: 21.2.13
 * Time: 22:37
 */
public class Dispose extends Module<DisposeSettings> {

    public Dispose(DisposeSettings disposeSettings, boolean defaultInitialize) {
        super(disposeSettings, defaultInitialize);
    }

    @Override
    protected void preInitialization() {
    }

    @Override
    protected void initializeDefaultValues() {
    }

    @Override
    protected void resetStates(ISimulator simulator) {

    }

    @Override
    public void logic(ISimulator simulator, Entity entity) {

    }

    @Override
    protected void setProperty() {

    }
}
