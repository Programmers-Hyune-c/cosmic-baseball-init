package com.hyunec.cosmicbaseballinit.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BattingResult {
    STRIKE(1, 0),
    BALL(0, 1),
    HIT(0, 0),
    DOUBLE_STRIKE(2, 0),
    DOUBLE_BALL(0, 2);

    private final int strikeCount;
    private final int ballCount;

    public static BattingResult getBattingResult(int value) {
        switch (value) {
            case 0:
                return STRIKE;
            case 1:
                return BALL;
            case 2:
                return DOUBLE_STRIKE;
            case 3:
                return DOUBLE_BALL;
            default:
                return HIT;
        }
    }
}
