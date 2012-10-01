package jDistsim.utils.logging.formatters;

import jDistsim.utils.logging.LogMessage;

/**
 * Author: Jirka Pénzeš
 * Date: 23.9.12
 * Time: 21:48
 */
public class DefaultLoggerFormatter implements ILoggerFormatter {

    @Override
    public String applyFormat(LogMessage logMessage) {
        return logMessage.getDateTime().toString() + ": " + logMessage.getLevel() + " ["
                + logMessage.getCallingClass() + " -> " + logMessage.getCallingMethod() + "()]: " + logMessage.getText();
    }
}
