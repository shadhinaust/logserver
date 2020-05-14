package com.dovetail.logserver.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeUtils {

    public static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm:ss");

    public static String toDateTimeString(LocalDateTime localDateTime) {
        return localDateTime.format(formatter);
    }
}
