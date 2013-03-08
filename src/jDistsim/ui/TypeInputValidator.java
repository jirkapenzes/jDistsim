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

    public String validateSpecialCharacters(String input, String inputName) throws TypeInputException {
        if (input.contains(" ")) {
            return (String) buildError(inputName);
        }
        return input;
    }


    public String validateComboBoxValue(int index, String value, String inputName) throws TypeInputException {
        return index == 0 ? (String) buildError(inputName) : value;
    }

    public void validateNull(Object object, String inputName) throws TypeInputException {
        if (object == null)
            buildError(inputName);
    }

    private Object buildError(String inputName) throws TypeInputException {
        String message = "Invalid " + inputName + " value ";
        showErrorDialog(message);
        throw new TypeInputException(message);
    }

    private void showErrorDialog(String message) {
        dialogBuilder.buildErrorDialog(message);
    }

    public String validateDuplicity(Iterable<String> otherData, String input, String inputName) throws TypeInputException {
        for (String key : otherData) {
            if (input.equals(key)) {
                return (String) buildError(inputName);
            }
        }
        return input;
    }
}
