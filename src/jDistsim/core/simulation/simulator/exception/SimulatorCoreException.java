package jDistsim.core.simulation.simulator.exception;

/**
 * Author: Jirka Pénzeš
 * Date: 22.2.13
 * Time: 0:37
 */
public class SimulatorCoreException extends Exception {
    public SimulatorCoreException(Exception innerException) {
        super(innerException.getMessage(), innerException.getCause());
    }
}
