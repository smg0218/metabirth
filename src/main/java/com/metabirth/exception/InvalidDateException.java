package com.metabirth.exception;

import java.text.ParseException;

public class InvalidDateException extends ParseException {
    /**
     * 날짜 관련해서 잘못 입력했을 경우 사용하는 커스텀 Exception
     * @param message : UI에 출력해줄 메세지
     */
    public InvalidDateException(String message) {
        super(message, 0);
    }
}
