package jDistsim.core.simulation.modules.lib.delay;

import jDistsim.core.simulation.modules.Module;
import jDistsim.core.simulation.modules.ModuleConfiguration;
import jDistsim.core.simulation.modules.common.ModuleProperty;
import jDistsim.core.simulation.simulator.ISimulator;
import jDistsim.core.simulation.simulator.entity.Entity;
import jDistsim.ui.module.ModuleView;

/**
 * Author: Jirka Pénzeš
 * Date: 21.2.13
 * Time: 22:37
 */
public class Delay extends Module {

    private int delayTime;

    public Delay(ModuleView view, ModuleConfiguration moduleConfiguration) {
        super(view, moduleConfiguration);
    }

    @Override
    protected void initializeDefaultValues() {
        delayTime = 1;
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

    @Override
    protected void setChildProperty() {
        getProperties().set(new ModuleProperty("delayTime", getDelayTime(), "delay time"));
    }

    public int getDelayTime() {
        return delayTime;
    }

    public void setDelayTime(int delayTime) {
        this.delayTime = delayTime;
    }
}
