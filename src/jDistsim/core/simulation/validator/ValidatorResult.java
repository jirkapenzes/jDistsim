package jDistsim.core.simulation.validator;

import java.util.List;

/**
 * Author: Jirka Pénzeš
 * Date: 21.2.13
 * Time: 22:52
 */
public class ValidatorResult {

    private List<ValidatorException> validatorExceptions;

    public ValidatorResult(List<ValidatorException> validatorExceptions) {
        this.validatorExceptions = validatorExceptions;
    }

    public boolean isValid() {
        return validatorExceptions.size() == 0;
    }

    public List<ValidatorException> getValidatorExceptions() {
        return validatorExceptions;
    }

    @Override
    public String toString() {
        String text = "";
        for (ValidatorException validatorException : validatorExceptions) {
            text += "\n" + validatorException.getOriginator() + " -> " + validatorException.getMessage();
        }
        return text;
    }
}
