package com.hyunec.cosmicbaseballinit.hitter.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum BattingsResult {

    OUT(3),
    FOUR_BALL(4);

    private final int value;
}
