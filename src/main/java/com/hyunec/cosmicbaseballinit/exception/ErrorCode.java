package com.hyunec.cosmicbaseballinit.exception;


import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    BAD_REQUEST("400", HttpStatus.BAD_REQUEST, "잘못된 요청"),
    INTERNAL_SERVER_ERROR("500", HttpStatus.INTERNAL_SERVER_ERROR, "내부 서버 에러");
    

    private final String code;
    private final HttpStatus status;
    private final String reason;
}
