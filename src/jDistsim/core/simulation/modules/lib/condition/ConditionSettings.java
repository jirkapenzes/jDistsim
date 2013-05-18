package jDistsim.core.simulation.modules.lib.condition;

import jDistsim.core.simulation.modules.ModuleSettings;

/**
 * Author: Jirka Pénzeš
 * Date: 24.3.13
 * Time: 12:59
 */
public class ConditionSettings extends ModuleSettings {

    public enum Operator {Equivalence, Inequality, Greater, Less, GreaterEquivalence, LessEquivalence}

    private String attributeName;
    private Operator operator;
    private double value;

    public ConditionSettings(String baseIdentifier) {
        super(baseIdentifier);
    }

    public String getAttributeName() {
        return attributeName;
    }

    public void setAttributeName(String attributeName) {
        this.attributeName = attributeName;
    }

    public Operator getOperator() {
        return operator;
    }

    public void setOperator(Operator operator) {
        this.operator = operator;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
