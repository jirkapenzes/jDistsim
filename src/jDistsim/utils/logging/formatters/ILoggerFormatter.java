package jDistsim.utils.logging.formatters;

import jDistsim.utils.logging.LogMessage;

/**
 * Author: Jirka Pénzeš
 * Date: 23.9.12
 * Time: 21:48
 */
public interface ILoggerFormatter {

    public String applyFormat(LogMessage logMessage);

}
