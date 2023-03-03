package com.hyunec.cosmicbaseballinit.service;

import lombok.Getter;

@Getter
public enum BattingResult {
    STRIKE(0), HIT(1), BALL(2), DOUBLE_STRIKE(3);

    private final int index;
    BattingResult(int index) {
        this.index = index;
    }
}
