package jDistsim.utils.logging;

import jDistsim.utils.logging.formatters.DefaultLoggerFormatter;

import java.util.Date;

/**
 * Author: Jirka Pénzeš
 * Date: 23.9.12
 * Time: 21:44
 */
public class LogMessage {

    private Date dateTime;
    private Logger.Level level;
    private String text;
    private String callingClass;
    private String callingMethod;

    public LogMessage() {
    }

    public LogMessage(Logger.Level level, String text, Date dateTime, String callingClass, String callingMethod) {
        this.dateTime = dateTime;
        this.level = level;
        this.text = text;
        this.callingClass = callingClass;
        this.callingMethod = callingMethod;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public Logger.Level getLevel() {
        return level;
    }

    public void setLevel(Logger.Level level) {
        this.level = level;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getCallingClass() {
        return callingClass;
    }

    public void setCallingClass(String callingClass) {
        this.callingClass = callingClass;
    }

    public String getCallingMethod() {
        return callingMethod;
    }

    public void setCallingMethod(String callingMethod) {
        this.callingMethod = callingMethod;
    }

    @Override
    public String toString() {
        return new DefaultLoggerFormatter().applyFormat(this);
    }
}
