package jDistsim.core.simulation.exception;

/**
 * Author: Jirka Pénzeš
 * Date: 18.2.13
 * Time: 22:52
 */
public class EventNotFoundException extends BaseSimulatorException {

    public EventNotFoundException() {
        super("Event not found - calendar is empty");
    }
}
