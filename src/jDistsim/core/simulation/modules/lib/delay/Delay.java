package jDistsim.core.simulation.modules.lib.delay;

import jDistsim.core.simulation.modules.ITimeAffectModule;
import jDistsim.core.simulation.modules.Module;
import jDistsim.core.simulation.modules.common.ModuleProperty;
import jDistsim.core.simulation.simulator.ISimulator;
import jDistsim.core.simulation.simulator.entity.Entity;

/**
 * Author: Jirka Pénzeš
 * Date: 21.2.13
 * Time: 22:37
 */
public class Delay extends Module<DelaySettings> implements ITimeAffectModule {

    public Delay(DelaySettings delaySettings, boolean defaultInitialize) {
        super(delaySettings, defaultInitialize);
    }

    @Override
    protected void preInitialization() {
    }

    @Override
    protected void initializeDefaultValues() {
        settings.setDelayTime(1);
    }

    @Override
    protected void resetStates(ISimulator simulator) {
    }

    @Override
    public void logic(ISimulator simulator, Entity entity) {
        double localTime = simulator.getLocalTime();

        for (Module module : getAllOutputDependencies())
            simulator.plan(localTime + settings.getDelayTime(), module, entity);
    }

    @Override
    protected void setProperty() {
        getProperties().set(new ModuleProperty("delayTime", settings.getDelayTime(), "delay time"));
    }

    @Override
    public double getMinimalAffectTime() {
        return settings.getDelayTime();
    }
}
