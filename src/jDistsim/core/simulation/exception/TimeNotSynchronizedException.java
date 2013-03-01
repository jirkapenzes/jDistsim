package jDistsim.core.simulation.exception;

/**
 * Author: Jirka Pénzeš
 * Date: 18.2.13
 * Time: 22:18
 */
public class TimeNotSynchronizedException extends BaseSimulatorException {

    public TimeNotSynchronizedException(double badTime, double localTime) {
        super("Time is not synchronized. [" + badTime + " < " + localTime + "]");
    }
}
