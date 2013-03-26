package jDistsim.core.simulation.exception;

/**
 * Author: Jirka Pénzeš
 * Date: 8.3.13
 * Time: 19:11
 */
public class RmiRemoteObjectNotFoundException extends BaseSimulatorException {
    public RmiRemoteObjectNotFoundException() {
        super("Remote object not found");
    }
}
