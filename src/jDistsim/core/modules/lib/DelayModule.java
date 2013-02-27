package jDistsim.core.modules.lib;

import jDistsim.core.modules.Module;
import jDistsim.core.modules.ModuleConfiguration;
import jDistsim.core.simulation.simulator.ISimulator;
import jDistsim.core.simulation.simulator.entity.Entity;
import jDistsim.ui.module.ModuleView;

/**
 * Author: Jirka Pénzeš
 * Date: 21.2.13
 * Time: 22:37
 */
public class DelayModule extends Module {

    private int delayTime = 1;

    public DelayModule(ModuleView view, ModuleConfiguration moduleConfiguration) {
        super(view, moduleConfiguration);
    }

    @Override
    protected void initializeDefaultValues() {
    }

    @Override
    protected void resetStates(ISimulator simulator) {

    }

    @Override
    public void logic(ISimulator simulator, Entity entity) {
        double localTime = simulator.getLocalTime();

        for (Module module : getAllOutputDependencies())
            simulator.plan(localTime + delayTime, module, entity);
    }


}
