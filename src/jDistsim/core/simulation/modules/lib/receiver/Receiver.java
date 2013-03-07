package jDistsim.core.simulation.modules.lib.receiver;

import jDistsim.core.simulation.modules.Module;
import jDistsim.core.simulation.modules.ModuleConfiguration;
import jDistsim.core.simulation.simulator.ISimulator;
import jDistsim.core.simulation.simulator.entity.Entity;
import jDistsim.ui.module.ModuleView;

/**
 * Author: Jirka Pénzeš
 * Date: 5.3.13
 * Time: 17:11
 */
public class Receiver extends Module {

    public Receiver(ModuleView view, ModuleConfiguration moduleConfiguration) {
        super(view, moduleConfiguration);
    }

    @Override
    protected void initializeDefaultValues() {
    }

    @Override
    protected void resetStates(ISimulator simulator) {
    }

    @Override
    protected void logic(ISimulator simulator, Entity entity) {
    }

    @Override
    protected void setChildProperty() {
    }
}
