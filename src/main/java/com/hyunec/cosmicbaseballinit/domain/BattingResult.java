package com.hyunec.cosmicbaseballinit.domain;

import lombok.Getter;

public enum BattingResult {
    STRIKE(1), BALL(1), HIT(0), DOUBLE_STRIKE(2), DOUBLE_BALL(2);

    @Getter
    private final int battingResultCount;

    BattingResult(int battingResultCount) {
        this.battingResultCount = battingResultCount;
    }
}
