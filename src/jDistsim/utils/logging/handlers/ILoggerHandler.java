package jDistsim.utils.logging.handlers;

import jDistsim.utils.logging.LogMessage;

/**
 * Author: Jirka Pénzeš
 * Date: 23.9.12
 * Time: 21:50
 */
public interface ILoggerHandler {

    public void publish(LogMessage logMessage);
}
