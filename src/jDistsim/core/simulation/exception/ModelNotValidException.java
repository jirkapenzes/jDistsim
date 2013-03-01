package jDistsim.core.simulation.exception;

import jDistsim.core.simulation.validator.ValidatorResult;

/**
 * Author: Jirka Pénzeš
 * Date: 21.2.13
 * Time: 23:58
 */
public class ModelNotValidException extends BaseSimulatorException {

    private ValidatorResult validatorResult;

    public ModelNotValidException() {
        super("Model not validate");
    }


    public ModelNotValidException(ValidatorResult validatorResult) {
        super("Model not validate" + validatorResult.toString());
        this.validatorResult = validatorResult;

    }

    public ValidatorResult getValidatorResult() {
        return validatorResult;
    }
}
