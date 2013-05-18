package jDistsim.core.simulation.modules.lib.assign;

import jDistsim.core.simulation.modules.Module;
import jDistsim.core.simulation.modules.common.ModuleProperty;
import jDistsim.core.simulation.simulator.ISimulator;
import jDistsim.core.simulation.simulator.entity.Attribute;
import jDistsim.core.simulation.simulator.entity.Entity;

import java.util.Random;

/**
 * Author: Jirka Pénzeš
 * Date: 3.3.13
 * Time: 13:39
 */
public class Assign extends Module<AssignSettings> {

    private static Random random = new Random(0);

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
            entity.getAttributes().set(processAttribute(attribute));
        }

        for (Module module : getAllOutputDependencies())
            simulator.plan(localTime, module, entity);
    }

    @Override
    protected void setProperty() {
        getProperties().set(new ModuleProperty("assignments", settings.size(), "assignments"));
    }


    private Attribute processAttribute(Attribute attribute) {
        String attValue = attribute.getValue();
        if (containsFunction(attValue, "rand")) {
            int[] values = parseFunction(attValue, "rand");
            int min = values[0];
            int max = values[1];
            int resultValue = random.nextInt(max - min + 1) + min;
            return new Attribute(attribute.getName(), String.valueOf(resultValue));
        }
        return attribute;
    }

    private int[] parseFunction(String expression, String functionName) {
        expression = expression.replace(functionName, "");
        expression = expression.replace("(", "");
        expression = expression.replace(")", "");
        String[] parameters = expression.split(",");

        int[] values = new int[parameters.length];
        for (int index = 0; index < values.length; index++) {
            values[index] = Integer.valueOf(parameters[index]);
        }
        return values;
    }


    private boolean containsFunction(String expression, String functionName) {
        if (expression.length() > functionName.length()) {
            if (expression.substring(0, functionName.length()).equals(functionName)) {
                return true;
            }
        }
        return false;
    }
}
