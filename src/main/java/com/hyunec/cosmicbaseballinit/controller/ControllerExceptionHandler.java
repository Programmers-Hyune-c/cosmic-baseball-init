package com.hyunec.cosmicbaseballinit.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class ControllerExceptionHandler {

    @ExceptionHandler
    protected ResponseEntity<String> handleException(Exception e){
        log.info(e.getMessage());
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

//    @ExceptionHandler
//    protected String handleException(Exception e){
//        log.info(e.getMessage());
//        return e.getMessage();
//    }

}
