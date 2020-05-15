package com.dovetail.logserver.log;

import com.dovetail.logserver.model.LogLevel;
import com.dovetail.logserver.model.LogType;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.ThreadContext;
import org.springframework.stereotype.Component;

@Log4j2
@Component
public class LogImpl implements Log {
    private static final String TYPE_KEY = "logType";
    private static final String DEFAULT_TYPE = LogType.EVENT.getLevel();

    @Override
    public void fatal(String message) {
        ThreadContext.put(TYPE_KEY, DEFAULT_TYPE);
        log.fatal(message);
        ThreadContext.clearAll();
    }

    @Override
    public void fatal(LogType type, String message) {
        ThreadContext.put(TYPE_KEY, type.getLevel());
        log.fatal(message);
        ThreadContext.clearAll();
    }

    @Override
    public void error(String message) {
        ThreadContext.put(TYPE_KEY, DEFAULT_TYPE);
        log.fatal(message);
        ThreadContext.clearAll();
    }

    @Override
    public void error(LogType type, String message) {
        ThreadContext.put(TYPE_KEY, type.getLevel());
        log.fatal(message);
        ThreadContext.clearAll();
    }

    @Override
    public void warn(String message) {
        ThreadContext.put(TYPE_KEY, DEFAULT_TYPE);
        log.fatal(message);
        ThreadContext.clearAll();
    }

    @Override
    public void warn(LogType type, String message) {
        ThreadContext.put(TYPE_KEY, type.getLevel());
        log.fatal(message);
        ThreadContext.clearAll();
    }

    @Override
    public void info(String message) {
        ThreadContext.put(TYPE_KEY, DEFAULT_TYPE);
        log.fatal(message);
        ThreadContext.clearAll();
    }

    @Override
    public void info(LogType type, String message) {
        ThreadContext.put(TYPE_KEY, type.getLevel());
        log.fatal(message);
        ThreadContext.clearAll();
    }

    @Override
    public void debug(String message) {
        ThreadContext.put(TYPE_KEY, DEFAULT_TYPE);
        log.fatal(message);
        ThreadContext.clearAll();
    }

    @Override
    public void debug(LogType type, String message) {
        ThreadContext.put(TYPE_KEY, type.getLevel());
        log.fatal(message);
        ThreadContext.clearAll();
    }

    @Override
    public void trace(String message) {
        ThreadContext.put(TYPE_KEY, DEFAULT_TYPE);
        log.fatal(message);
        ThreadContext.clearAll();
    }

    @Override
    public void trace(LogType type, String message) {
        ThreadContext.put(TYPE_KEY, type.getLevel());
        log.fatal(message);
        ThreadContext.clearAll();
    }

    @Override
    public void log(String message) {
        ThreadContext.put(TYPE_KEY, DEFAULT_TYPE);
        log.info(message);
        ThreadContext.clearAll();
    }

    @Override
    public void log(LogLevel level, String message) {
        ThreadContext.put(TYPE_KEY, DEFAULT_TYPE);
        printLog(level, message);
        ThreadContext.clearAll();
    }

    @Override
    public void log(LogType type, LogLevel level, String message) {
        ThreadContext.put(TYPE_KEY, type.getLevel());
        printLog(level, message);
        ThreadContext.clearAll();
    }

    private void printLog(LogLevel level, String message) {
        switch (level) {
            case FATAL:
                log.fatal(message);
                break;
            case ERROR:
                log.error(message);
                break;
            case WARN:
                log.warn(message);
                break;
            case INFO:
                log.info(message);
                break;
            case DEBUG:
                log.debug(message);
                break;
            case TRACE:
                log.trace(message);
                break;
        }
    }
}
