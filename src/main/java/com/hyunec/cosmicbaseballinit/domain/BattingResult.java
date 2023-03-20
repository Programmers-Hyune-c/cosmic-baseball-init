package com.hyunec.cosmicbaseballinit.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum BattingResult {
    STRIKE(1, 0),
    BALL(0, 1),
    HIT(0, 0),
    DOUBLE_STRIKE(2, 0),
    DOUBLE_BALL(0, 2),
    BULL_EYE_STRIKE(3, 0),
    BULL_EYE_BALL(0, 4);

    public BattingResult toBullEyeResult(){
        return this == STRIKE ? BULL_EYE_STRIKE : BULL_EYE_BALL;
    }

    private final int increaseStrikeCount;
    private final int increaseBallCount;
}
