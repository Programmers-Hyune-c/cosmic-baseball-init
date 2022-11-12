package com.hyunec.cosmicbaseballinit.domain.baseball.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Batting {
    STRIKE(0.33),
    BALL(0.33),
    HIT(0.33),
    ;

    private final double rate;
}
