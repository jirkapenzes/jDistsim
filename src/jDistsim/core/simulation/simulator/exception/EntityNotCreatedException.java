package jDistsim.core.simulation.simulator.exception;

/**
 * Author: Jirka Pénzeš
 * Date: 22.2.13
 * Time: 17:19
 */
public class EntityNotCreatedException extends BaseSimulatorException{
    public EntityNotCreatedException(String originModuleIdentifier) {
        super("New module not plan in module " + originModuleIdentifier);
    }
}
