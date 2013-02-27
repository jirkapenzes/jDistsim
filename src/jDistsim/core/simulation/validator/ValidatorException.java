package jDistsim.core.simulation.validator;

/**
 * Author: Jirka Pénzeš
 * Date: 21.2.13
 * Time: 22:54
 */
public class ValidatorException {

    private String originator;
    private String message;

    public ValidatorException(String originator, String message) {
        this.originator = originator;
        this.message = message;
    }

    public String getOriginator() {
        return originator;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "ValidatorException{" +
                "originator='" + originator + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
