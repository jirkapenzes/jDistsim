package jDistsim.core.simulation.simulator;

/**
 * Author: Jirka Pénzeš
 * Date: 18.2.13
 * Time: 21:38
 */
public class SimulatorEnvironment {

    private String modelName;
    private double localTime;

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public double getLocalTime() {
        return localTime;
    }

    public void setLocalTime(double localTime) {
        this.localTime = localTime;
    }
}
