package jDistsim.core.simulation.simulator.exception;

/**
 * Author: Jirka Pénzeš
 * Date: 22.2.13
 * Time: 0:36
 */
public class ThreadException extends BaseSimulatorException {

    protected ThreadException() {
        super("Unexpected simulator thread exception");
    }
}
