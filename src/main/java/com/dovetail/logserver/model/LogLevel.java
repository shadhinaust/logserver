package com.dovetail.logserver.model;

import java.util.Arrays;
import java.util.Objects;

import com.dovetail.logserver.exception.NotFoundException;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum LogLevel {

    FATAL(1, "FATAL", 100),
    ERROR(2, "ERROR", 200),
    WARN(3, "WARN", 300),
    INFO(4, "INFO", 400),
    DEBUG(5, "DEBUG", 500),
    TRACE(6, "TRACE", 600);

    private int id;
    private String stdLevel;
    private int intLevel;

    public static LogLevel getById(int id) {
        return Arrays.stream(LogLevel.values()).filter(level -> Objects.equals(level.getId(), id)).findFirst()
                .orElseThrow(NotFoundException::new);
    }
}
