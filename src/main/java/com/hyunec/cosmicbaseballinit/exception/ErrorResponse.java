package com.hyunec.cosmicbaseballinit.exception;

import lombok.Getter;

@Getter
public class ErrorResponse {

    private String message;


    private ErrorResponse(String message) {
        this.message = message;
    }

    public static ErrorResponse of(String message){
        return new ErrorResponse(message);
    }
}
