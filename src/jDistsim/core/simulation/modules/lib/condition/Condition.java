package jDistsim.core.simulation.modules.lib.condition;

import jDistsim.core.simulation.modules.Module;
import jDistsim.core.simulation.modules.ModuleConfiguration;
import jDistsim.core.simulation.simulator.ISimulator;
import jDistsim.core.simulation.simulator.entity.Entity;

/**
 * Author: Jirka Pénzeš
 * Date: 13.3.13
 * Time: 23:59
 */
public class Condition extends Module<ConditionSettings> {

    public Condition(ConditionSettings conditionSettings) {
        super(conditionSettings);
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
