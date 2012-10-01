package jDistsim.utils.logging.module;

import jDistsim.utils.logging.LogMessage;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: Jirka Pénzeš
 * Date: 23.9.12
 * Time: 21:56
 */
public class ModuleManager {

    private List<LoggerModule> modules;

    public ModuleManager() {
        modules = new ArrayList<LoggerModule>();
    }

    public void beforeLog() {
        for (LoggerModule loggerModule : modules) {
            loggerModule.beforeLog();
        }
    }

    public void afterLog(LogMessage logMessage) {
        for (LoggerModule loggerModule : modules) {
            loggerModule.afterLog(logMessage);
        }
    }

    public void exceptionLog(Exception exception) {
        for (LoggerModule loggerModule : modules) {
            loggerModule.exceptionLog(exception);
        }
    }

    public List<LoggerModule> getModules() {
        return modules;
    }
}
