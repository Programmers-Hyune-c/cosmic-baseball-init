package com.hyunec.cosmicbaseballinit.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class ControllerExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler
    protected ErrorResponse handleIllegalArgumentException(IllegalArgumentException e){
        log.error("handleIllegalArgumentException", e);
        return ErrorResponse.of(ErrorCode.BAD_REQUEST, e);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler
    protected ErrorResponse handleException(Exception e){
        log.error("exceptionHandler", e);
        return ErrorResponse.of(ErrorCode.INTERNAL_SERVER_ERROR, e);
    }
}
