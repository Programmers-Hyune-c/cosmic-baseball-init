package com.hyunec.cosmicbaseballinit.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public class ErrorResponse {

    //TODO: Error 정보 추가하기
    private String message;
    private String code;

    public ErrorResponse(HttpStatus httpStatus) {
        this.message = httpStatus.getReasonPhrase();
        this.code = httpStatus.name();
    }
}
