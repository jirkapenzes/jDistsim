package jDistsim.utils.logging.handlers;

import jDistsim.utils.logging.LogMessage;

import java.util.Iterator;

/**
 * Author: Jirka Pénzeš
 * Date: 23.9.12
 * Time: 21:58
 */
public interface ILoggerHandlerManager {

    void publish(LogMessage logMessage);

    ILoggerHandlerManager addHandler(ILoggerHandler loggerHandler);

    boolean removeHandler(ILoggerHandler loggerHandler);

    Iterator<LogMessage> getAllMessages();
}
