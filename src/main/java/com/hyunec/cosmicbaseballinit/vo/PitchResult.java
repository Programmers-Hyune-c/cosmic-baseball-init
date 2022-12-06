package com.hyunec.cosmicbaseballinit.vo;

import lombok.Getter;
import org.springframework.context.annotation.Bean;

@Getter
public enum PitchResult {
    Strike("Strike"),
    Ball("Ball"),
    Hit("Hit");


    private String resultName;

    PitchResult(String resultName) {
        this.resultName = resultName;
    }


}
