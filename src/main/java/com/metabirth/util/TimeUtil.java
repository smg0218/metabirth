package com.metabirth.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TimeUtil {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    /**
     * LocalDateTime을 yyyy-MM-dd HH:mm:ss 형태의 String으로 변환하는 메서드
     * @param dateTime : 변환을 원하는 LocalDateTime
     * @return : String 타입의 yyyy-MM-dd HH:mm:ss 형태로 변환된 LocalDateTime
     */
    public static String formatLocalDateTime(LocalDateTime dateTime) {
        return dateTime.format(FORMATTER);
    }
}
