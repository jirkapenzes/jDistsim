package jDistsim.core.simulation.simulator;

/**
 * Author: Jirka Pénzeš
 * Date: 18.2.13
 * Time: 22:27
 */
public interface ISimulatorEndCondition {

    boolean occurred(SimulatorEnvironment environment);

}
