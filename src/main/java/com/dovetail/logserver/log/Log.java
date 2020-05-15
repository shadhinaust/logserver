package com.dovetail.logserver.log;

import com.dovetail.logserver.model.LogLevel;
import com.dovetail.logserver.model.LogType;

public interface Log {
    void fatal(String message);

    void fatal(LogType type, String message);

    void error(String message);

    void error(LogType type, String message);

    void warn(String message);

    void warn(LogType type, String message);

    void info(String message);

    void info(LogType type, String message);

    void debug(String message);

    void debug(LogType type, String message);

    void trace(String message);

    void trace(LogType type, String message);

    void log(String message);

    void log(LogLevel level, String message);

    void log(LogType type, LogLevel level, String message);
}
