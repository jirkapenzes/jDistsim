package jDistsim.utils.logging.handlers;

import jDistsim.utils.logging.LogMessage;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Author: Jirka Pénzeš
 * Date: 23.9.12
 * Time: 21:58
 */
public class LoggerHandlerManager implements ILoggerHandlerManager {

    private List<ILoggerHandler> loggerHandlers;
    private List<LogMessage> messages;

    public LoggerHandlerManager() {
        loggerHandlers = new ArrayList<ILoggerHandler>();
        messages = new ArrayList<LogMessage>();
    }

    @Override
    public void publish(LogMessage logMessage) {
        messages.add(logMessage);
        for (ILoggerHandler loggerHandler : loggerHandlers) {
            loggerHandler.publish(logMessage);
        }
    }

    @Override
    public ILoggerHandlerManager addHandler(ILoggerHandler loggerHandler) {
        if (loggerHandler != null) {
            loggerHandlers.add(loggerHandler);
        }
        return this;
    }

    @Override
    public boolean removeHandler(ILoggerHandler loggerHandler) {
        return loggerHandlers.remove(loggerHandler);
    }

    @Override
    public Iterator<LogMessage> getAllMessages() {
        return messages.iterator();
    }
}
