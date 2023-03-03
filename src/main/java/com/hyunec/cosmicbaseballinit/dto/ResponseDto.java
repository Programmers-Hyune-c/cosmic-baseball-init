package com.hyunec.cosmicbaseballinit.dto;

import java.util.Map;
import lombok.Getter;

@Getter
public class ResponseDto {

    private Map<String, Integer> ballCount;

    public ResponseDto() {
    }

    public ResponseDto(Map<String, Integer> ballCount) {
        this.ballCount = ballCount;
    }
}
