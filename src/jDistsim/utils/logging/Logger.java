package jDistsim.utils.logging;

import jDistsim.utils.logging.handlers.ConsoleLoggerHandler;
import jDistsim.utils.logging.handlers.ILoggerHandlerManager;
import jDistsim.utils.logging.handlers.LoggerHandlerManager;
import jDistsim.utils.logging.module.LoggerModule;
import jDistsim.utils.logging.module.ModuleManager;
import jDistsim.utils.pattern.observer.Observable;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * Author: Jirka Pénzeš
 * Date: 23.9.12
 * Time: 21:45
 */
public class Logger {

    private static final Object lock = new Object();
    private static ILoggerHandlerManager loggerHandlerManager;
    private static ModuleManager moduleManager;
    private static Observable observable;
    private static Level defaultLevel = Level.Info;
    private static boolean isTurned = true;
    private static boolean isTurnedDebug = true;

    public enum Level {None, Info, Warning, Error, Severe, Fine, Debug}

    static {
        synchronized (lock) {
            loggerHandlerManager = new LoggerHandlerManager();
            moduleManager = new ModuleManager();
            observable = new Observable();
        }
    }

    public static void defaultInitialize() {
        getLoggerHandlerManager().addHandler(new ConsoleLoggerHandler());
        log(Level.Info, "Default logger initialization");
    }

    public static Level getDefaultLevel() {
        return defaultLevel;
    }

    public static void setDefaultLevel(Level level) {
        defaultLevel = level;
    }

    public static ILoggerHandlerManager getLoggerHandlerManager() {
        return loggerHandlerManager;
    }

    public static void log() {
        log("There is no message");
    }

    public static void log(Object object) {
        log(object.toString());
    }

    public static void log(String message) {
        log(defaultLevel, message);
    }

    public static void log(Level level, String message) {
        StackTraceElement element = getCallingStackTraceElement();
        log(level, message, element.getClassName(), element.getMethodName());
    }

    public static void log(Exception exception) {
        log(Level.Error, exception.getMessage());
        moduleManager.exceptionLog(exception);
    }

    private static void log(Level level, String message, String callingClass, String callingMethod) {
        if (!isTurned || (!isTurnedDebug && level == Level.Debug))
            return;

        Date currentDateTime = new Date();

        moduleManager.beforeLog();
        LogMessage logMessage = new LogMessage(level, message, currentDateTime, callingClass, callingMethod);

        loggerHandlerManager.publish(logMessage);
        moduleManager.afterLog(logMessage);
    }

    public static void on() {
        isTurned = true;
    }

    public static void off() {
        isTurned = false;
    }

    public static void debugOn() {
        isTurnedDebug = true;
    }

    public static void debugOff() {
        isTurnedDebug = false;
    }

    public static List<LoggerModule> getModules() {
        return moduleManager.getModules();
    }

    public static Iterator<LogMessage> getMessages() {
        return loggerHandlerManager.getAllMessages();
    }

    private static StackTraceElement getCallingStackTraceElement() {
        Throwable t = new Throwable();
        StackTraceElement[] elements = t.getStackTrace();

        if (elements.length == 0) {
            return null;
        }

        for (StackTraceElement element : elements) {
            if (!element.getFileName().equals("Logger.java")) {
                return element;
            }
        }

        return elements[elements.length - 1];
    }

    public static Observable getObservable() {
        return observable;
    }
}
