package com.hyunec.cosmicbaseballinit.dto;

import lombok.Getter;

@Getter
public class ResponseDto {
    private String result;


    public ResponseDto(String result) {
        this.result = result;
    }
}
