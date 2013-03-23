package jDistsim.core.simulation.modules.lib.dispose;

import jDistsim.core.simulation.modules.Module;
import jDistsim.core.simulation.modules.ModuleConfiguration;
import jDistsim.core.simulation.simulator.ISimulator;
import jDistsim.core.simulation.simulator.entity.Entity;

/**
 * Author: Jirka Pénzeš
 * Date: 21.2.13
 * Time: 22:37
 */
public class Dispose extends Module {

    public Dispose(ModuleConfiguration moduleConfiguration) {
        super(moduleConfiguration);
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
    protected void setChildProperty() {

    }
}
