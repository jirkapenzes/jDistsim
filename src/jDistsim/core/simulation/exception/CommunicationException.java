package jDistsim.core.simulation.exception;

/**
 * Author: Jirka Pénzeš
 * Date: 8.3.13
 * Time: 12:20
 */
public class CommunicationException extends BaseSimulatorException {
    public CommunicationException() {
        super("Distributed model not registred");
    }
}
