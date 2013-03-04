package jDistsim.core.simulation.modules.lib.assign;

import jDistsim.core.simulation.modules.Module;
import jDistsim.core.simulation.modules.ModuleConfiguration;
import jDistsim.core.simulation.modules.common.ModuleProperty;
import jDistsim.core.simulation.simulator.ISimulator;
import jDistsim.core.simulation.simulator.entity.AttributeCollection;
import jDistsim.core.simulation.simulator.entity.Entity;
import jDistsim.ui.module.ModuleView;

/**
 * Author: Jirka Pénzeš
 * Date: 3.3.13
 * Time: 13:39
 */
public class Assign extends Module {

    private AttributeCollection attributes;

    public Assign(ModuleView view, ModuleConfiguration moduleConfiguration) {
        super(view, moduleConfiguration);
    }

    @Override
    protected void initializeDefaultValues() {
        attributes = new AttributeCollection();
    }

    @Override
    protected void resetStates(ISimulator simulator) {
    }

    @Override
    protected void logic(ISimulator simulator, Entity entity) {
        double localTime = simulator.getLocalTime();

        for (Module module : getAllOutputDependencies())
            simulator.plan(localTime, module, entity);
    }

    @Override
    protected void setChildProperty() {
        getProperties().set(new ModuleProperty("assignments", attributes.size(), "assignments"));
    }

    public AttributeCollection getAttributes() {
        return attributes;
    }
}
