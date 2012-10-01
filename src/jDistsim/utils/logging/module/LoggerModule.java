package jDistsim.utils.logging.module;

import jDistsim.utils.logging.LogMessage;

/**
 * Author: Jirka Pénzeš
 * Date: 23.9.12
 * Time: 21:56
 */
public abstract class LoggerModule {

    public void beforeLog() {
    }

    public void afterLog(LogMessage logMessage) {
    }

    public void exceptionLog(Exception exception) {
    }
}
