package com.hyunec.cosmicbaseballinit.vo;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;

@Getter
@RequiredArgsConstructor
public enum PitchResult {
    STRIKE("STRIKE"),
    BALL("BALL"),
    HIT("HIT");


    private final String resultName;



}
