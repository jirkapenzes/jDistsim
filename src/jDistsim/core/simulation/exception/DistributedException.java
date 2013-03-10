package jDistsim.core.simulation.exception;

/**
 * Author: Jirka Pénzeš
 * Date: 8.3.13
 * Time: 15:59
 */
public class DistributedException extends BaseSimulatorException {

    public DistributedException() {
        super("Distributed error");
    }

    public DistributedException(String message) {
        super(message);
    }
}
