package com.metabirth.util;

public class PatternUtil {
    private static final String PHONE_PATTERN = "010-\\d{4}-\\d{4}";

    /**
     * String 입력값이 010-xxxx-xxxx 와 같은 형태인지 확인하는 메서드
     * @param phone String 형태의 입력값
     * @return 패턴이 일치하면 true, 아니먄 false 반환
     */
    public static boolean checkPhonePattern(String phone) {
        return phone.matches(PHONE_PATTERN);
    }
}
