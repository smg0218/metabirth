package com.metabirth.exception;

import java.text.ParseException;

public class InvalidDateException extends ParseException {
    public InvalidDateException(String message) {
        super(message, 0);
    }
}
