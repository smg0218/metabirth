package com.metabirth.util;

import java.util.regex.Pattern;

/**
 * input 값이 패턴에 맞는지 확인하는 Util
 */
public class PatternUtil {
    private static final String PHONE_PATTERN = "010-\\d{4}-\\d{4}";
    private static final String DATE_PATTERN = "\\d{4}-\\d{2}-\\d{2}";

    /**
     * String 입력값이 010-xxxx-xxxx 와 같은 형태인지 확인하는 메서드
     * @param phone String 형태의 입력값
     * @return 패턴이 일치하면 true, 아니먄 false 반환
     */
    public static boolean checkPhonePattern(String phone) {
        return phone.matches(PHONE_PATTERN);
    }

    /**
     * String 입력 값이 yyyy-MM-dd 형태인지 확인하는 메서드
     * @param date String 형태의 입력값
     * @return 패턴이 일치하면 true, 아니면 false 반환
     */
    public static boolean checkDatePattern(String date) {
        return Pattern.matches(DATE_PATTERN, date);
    }
}
