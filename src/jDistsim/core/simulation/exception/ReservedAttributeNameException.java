package jDistsim.core.simulation.exception;

/**
 * Author: Jirka Pénzeš
 * Date: 22.2.13
 * Time: 16:49
 */
public class ReservedAttributeNameException extends BaseSimulatorException {

    public ReservedAttributeNameException(String attributeName) {
        super("Attribute name " + attributeName + " is a reserved word");
    }
}
