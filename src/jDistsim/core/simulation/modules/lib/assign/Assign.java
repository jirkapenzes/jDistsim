package jDistsim.core.simulation.modules.lib.assign;

import jDistsim.core.simulation.modules.Module;
import jDistsim.core.simulation.modules.common.ModuleProperty;
import jDistsim.core.simulation.simulator.ISimulator;
import jDistsim.core.simulation.simulator.entity.Attribute;
import jDistsim.core.simulation.simulator.entity.Entity;

/**
 * Author: Jirka Pénzeš
 * Date: 3.3.13
 * Time: 13:39
 */
public class Assign extends Module<AssignSettings> {

    public Assign(AssignSettings assignSettings, boolean defaultInitialize) {
        super(assignSettings, defaultInitialize);
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
    protected void logic(ISimulator simulator, Entity entity) {
        double localTime = simulator.getLocalTime();

        for (Attribute attribute : settings.getAttributes()) {
            entity.getAttributes().set(attribute);
        }

        for (Module module : getAllOutputDependencies())
            simulator.plan(localTime, module, entity);
    }

    @Override
    protected void setChildProperty() {
        getProperties().set(new ModuleProperty("assignments", settings.size(), "assignments"));
    }
}
