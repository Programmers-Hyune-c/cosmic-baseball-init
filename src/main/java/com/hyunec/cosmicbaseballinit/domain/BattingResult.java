package com.hyunec.cosmicbaseballinit.domain;

import lombok.Getter;

@Getter
public enum BattingResult {
    BULL_EYE_STRIKE(3,0),
    BULL_EYE_BALL(0,4),
    STRIKE(1,0),
    BALL(0,1),
    HIT(0,0),
    DOUBLE_STRIKE(2,0),
    DOUBLE_BALL(0,2);

    private final int increaseStrikeCount;
    private final int increaseBallCount;

    BattingResult(int increaseStrikeCount, int increaseBallCount) {
        this.increaseStrikeCount = increaseStrikeCount;
        this.increaseBallCount = increaseBallCount;
    }
}
