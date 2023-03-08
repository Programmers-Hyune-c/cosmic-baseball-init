package com.hyunec.cosmicbaseballinit.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ResponseDto {

    private final String result;
    private final int strikeCount;
    private final int ballCount;

    @Builder
    public ResponseDto(String result, int strikeCount, int ballCount) {
        this.result = result;
        this.strikeCount = strikeCount;
        this.ballCount = ballCount;
    }
}
