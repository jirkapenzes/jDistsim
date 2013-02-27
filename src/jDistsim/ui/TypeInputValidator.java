package jDistsim.ui;

import jDistsim.ServiceLocator;
import jDistsim.application.designer.common.IDialogBuilder;
import jDistsim.ui.exception.TypeInputException;
import jDistsim.utils.logging.Logger;

/**
 * Author: Jirka Pénzeš
 * Date: 26.2.13
 * Time: 23:36
 */
public class TypeInputValidator {

    private IDialogBuilder dialogBuilder;

    public TypeInputValidator() {
        dialogBuilder = ServiceLocator.getInstance().get(IDialogBuilder.class);
    }

    public int validateInteger(String input, String inputName) throws TypeInputException {
        try {
            validateString(input, inputName);
            return Integer.valueOf(input);
        } catch (Exception exception) {
            return (int) buildError(inputName);
        }
    }

    public double validateDouble(String input, String inputName) throws TypeInputException {
        try {
            validateString(input, inputName);
            return Double.valueOf(input);
        } catch (Exception exception) {
            Logger.log(exception);
            return (double) buildError(inputName);
        }
    }

    public String validateString(String input, String inputName) throws TypeInputException {
        if (input.trim().equals("")) {
            return (String) buildError(inputName);
        }
        return input;
    }

    private Object buildError(String inputName) throws TypeInputException {
        String message = "Invalid " + inputName + " value ";
        showErrorDialog(message);
        throw new TypeInputException(message);
    }

    private void showErrorDialog(String message) {
        dialogBuilder.buildErrorDialog(message);
    }
}