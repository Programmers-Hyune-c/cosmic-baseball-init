package com.hyunec.cosmicbaseballinit.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BattingResult {
    STRIKE,
    BALL,
    HIT,
    DOUBLE_STRIKE,
    DOUBLE_BALL;

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
