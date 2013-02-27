package jDistsim.core.simulation.simulator.exception;

/**
 * Author: Jirka Pénzeš
 * Date: 18.2.13
 * Time: 22:17
 */
public abstract class BaseSimulatorException extends RuntimeException {

    protected BaseSimulatorException(String message) {
        super(message);
    }
}
