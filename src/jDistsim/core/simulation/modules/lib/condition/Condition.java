package jDistsim.core.simulation.modules.lib.condition;

import jDistsim.core.simulation.modules.Module;
import jDistsim.core.simulation.simulator.ISimulator;
import jDistsim.core.simulation.simulator.entity.Attribute;
import jDistsim.core.simulation.simulator.entity.Entity;

/**
 * Author: Jirka Pénzeš
 * Date: 13.3.13
 * Time: 23:59
 */
public class Condition extends Module<ConditionSettings> {

    public Condition(ConditionSettings conditionSettings, boolean defaultInitialize) {
        super(conditionSettings, defaultInitialize);
    }

    @Override
    protected void preInitialization() {
    }

    @Override
    protected void initializeDefaultValues() {
        getSettings().setAttributeName("id");
        getSettings().setOperator(ConditionSettings.Operator.Inequality);
        getSettings().setValue(0);
    }

    @Override
    protected void resetStates(ISimulator simulator) {
    }

    @Override
    protected void logic(ISimulator simulator, Entity entity) {
        if (condition(entity)) {
            for (Module module : getOutputConnectedPoints().get(0).getDependencies()) {
                simulator.plan(simulator.getLocalTime(), module, entity);
            }
        } else {
            for (Module module : getOutputConnectedPoints().get(1).getDependencies()) {
                simulator.plan(simulator.getLocalTime(), module, entity);
            }
        }

    }

    private boolean condition(Entity entity) {
        try {
            Attribute attribute = entity.getAttributes().get(getSettings().getAttributeName());
            double attributeValue = Double.valueOf(attribute.getValue());
            double conditionValue = getSettings().getValue();
            switch (getSettings().getOperator()) {
                case Equivalence:
                    return attributeValue == conditionValue;
                case Inequality:
                    return attributeValue != conditionValue;
                case Greater:
                    return attributeValue > conditionValue;
                case GreaterEquivalence:
                    return attributeValue >= conditionValue;
                case Less:
                    return attributeValue < conditionValue;
                case LessEquivalence:
                    return attributeValue <= conditionValue;
            }
            return true;
        } catch (Exception exception) {
            return false;
        }
    }

    @Override
    protected void setProperty() {
    }
}
