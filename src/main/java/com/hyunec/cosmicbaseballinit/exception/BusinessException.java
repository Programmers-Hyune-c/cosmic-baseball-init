package com.hyunec.cosmicbaseballinit.exception;

public class BusinessException extends RuntimeException {

    public BusinessException(ExceptionType exceptionType) {
        super(exceptionType.getMessage());
    }
}
