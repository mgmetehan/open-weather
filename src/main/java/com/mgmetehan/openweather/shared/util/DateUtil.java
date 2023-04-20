package com.mgmetehan.openweather.shared.util;

import lombok.experimental.UtilityClass;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@UtilityClass
public class DateUtil {
    private final Clock clock = null;

    public LocalDateTime getLocalDateTimeNow() {
        Instant instant = clock.instant();
        return LocalDateTime.ofInstant(instant, clock.getZone());
    }

    public LocalDateTime localDateTimeFormat(String localtime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        var result = LocalDateTime.parse(localtime, formatter);
        return result;
    }
}
