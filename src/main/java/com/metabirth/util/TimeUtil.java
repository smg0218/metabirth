package com.metabirth.util;

import com.metabirth.exception.InvalidDateException;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;

public class TimeUtil {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
    private static final String DATE_PATTERN = "\\d{4}-\\d{2}-\\d{2}";
    static {
        DATE_FORMAT.setLenient(false); // 실제로 존재하는 날짜인지 확인
    }
    /**
     * LocalDateTime을 yyyy-MM-dd HH:mm:ss 형태의 String으로 변환하는 메서드
     * @param dateTime : 변환을 원하는 LocalDateTime
     * @return : String 타입의 yyyy-MM-dd HH:mm:ss 형태로 변환된 LocalDateTime
     */
    public static String formatDateTimeToString(LocalDateTime dateTime) {
        return dateTime.format(DATE_TIME_FORMATTER);
    }

    /**
     * yyyy-MM-dd HH:mm:ss 형태로 입력받은 String 타입을 LocalDateTime으로 변환하는 메서드
     * @param dateTime : yyyy-MM-dd HH:mm:ss 형태로 입력받은 String 값
     * @return : LocalDateTime 형태로 변환된 입력값
     */
    public static LocalDateTime formatStringToDateTime(String dateTime) {
        return LocalDateTime.parse(dateTime, DATE_TIME_FORMATTER);
    }

    /**
     * yyyy-MM-dd 형태의 String 데이터를 sql.Date 타입으로 변환하는 메서드
     * @param date : yyyy-MM-dd 형태의 String 데이터
     * @return : sql.Date 타입으로 변환된 yyyy-MM-dd
     * @throws InvalidDateException : 변환 도중 확인된 문제 발생
     * @throws ParseException : 변환 실패
     */
    public static Date formatStringDateToSqlDate(String date) throws ParseException {
        // 입력받은 데이터 형식이 [yyyy-MM-dd] 형태인지 확인
        if (!Pattern.matches(DATE_PATTERN, date)) {
            throw new InvalidDateException("날짜 형식은 [yyyy-mm-dd]와 같아야 합니다! \n예)2025-01-01");
        }

        java.util.Date utilDate = DATE_FORMAT.parse(date);
        Date sqlDate = new java.sql.Date(utilDate.getTime());

        LocalDate inputDate = sqlDate.toLocalDate();
        LocalDate today = LocalDate.now(ZoneId.systemDefault());

        if (inputDate.isAfter(today)) {
            throw new InvalidDateException("미래 날짜는 허용되지 않습니다! \n오늘날짜: " + today +
                    " \n입력날짜: " + inputDate);
        }

        return sqlDate;
    }


}
