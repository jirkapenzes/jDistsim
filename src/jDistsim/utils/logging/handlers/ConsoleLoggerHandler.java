package jDistsim.utils.logging.handlers;

import jDistsim.utils.logging.formatters.DefaultLoggerFormatter;
import jDistsim.utils.logging.formatters.ILoggerFormatter;
import jDistsim.utils.logging.LogMessage;

/**
 * Author: Jirka Pénzeš
 * Date: 23.9.12
 * Time: 21:52
 */
public class ConsoleLoggerHandler implements ILoggerHandler {

    private ILoggerFormatter loggerFormatter;

    public ConsoleLoggerHandler() {
        this(new DefaultLoggerFormatter());
    }

    public ConsoleLoggerHandler(ILoggerFormatter loggerFormatter) {
        this.loggerFormatter = loggerFormatter;
    }

    @Override
    public void publish(LogMessage logMessage) {
        System.out.println(loggerFormatter.applyFormat(logMessage));
    }
}
