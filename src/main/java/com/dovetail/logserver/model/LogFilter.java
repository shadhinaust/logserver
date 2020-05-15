package com.dovetail.logserver.model;

import com.dovetail.logserver.exception.NotFoundException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Arrays;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum LogFilter {
    RECENT(1, "1_HOUR", 1L, "Last 1 hour"),
    LAST_12_HOURS(2, "12_HOURS", 12L, "Last 12 hours"),
    LAST_24_HOURS(3, "24_HOUR", 24L, "Last 24 hours"),
    LAST_3_DAYS(4, "3_DAYS", 3L, "Last 3 days"),
    LAST_7_DAYS(5, "7_DAYS", 7L, "Last 7 days"),
    LAST_15_DAYS(6, "15_DAYS", 15L, "Last 15 days"),
    LAST_1_MONTH(7, "1_MONTH", 1L, "Last 1 month"),
    ALL_TIME(8, "ALL", null, "All time");

    private int id;
    private String key;
    private Long value;
    private String name;

    public static LogFilter getByKey(String key) {
        return Arrays.stream(LogFilter.values())
                .filter(logFilter -> logFilter.getKey().equals(key))
                .findFirst()
                .orElseThrow(NotFoundException::new);
    }
}
