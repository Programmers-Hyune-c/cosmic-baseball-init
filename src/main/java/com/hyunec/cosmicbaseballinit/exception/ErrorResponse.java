package com.hyunec.cosmicbaseballinit.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
@Builder
@RequiredArgsConstructor
public class ErrorResponse {

    private final String message;
    private final String code;
    private final LocalDateTime time = LocalDateTime.now();

    public static ErrorResponse of(ErrorCode errorCode){
        return ErrorResponse.builder()
                .message(errorCode.getReason())
                .code(errorCode.getCode())
                .build();
    }

    public static ErrorResponse of(ErrorCode errorCode, Exception ex){
        return ErrorResponse.builder()
                .message(ex.getMessage())
                .code(errorCode.getCode())
                .build();
    }
}
