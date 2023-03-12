package com.hyunec.cosmicbaseballinit.exception.controller;

import com.hyunec.cosmicbaseballinit.exception.NewBattingException;
import com.hyunec.cosmicbaseballinit.exception.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler(NewBattingException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse newBattingExceptionHandler(NewBattingException e) {
        return ErrorResponse.of(e.getMessage());
    }

}
