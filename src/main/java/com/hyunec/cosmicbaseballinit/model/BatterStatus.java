package com.hyunec.cosmicbaseballinit.model;

import lombok.Getter;

@Getter
public enum BatterStatus {
    STAY,
    ADVANCE,
    OUT;

    public static BatterStatus getBatterStatus(BatterResult result) {
        if (result.getBallCount() == 4 || result.getHitCount() == 1) {
            return ADVANCE;
        }
        if (result.getStrikeCount() == 3) {
            return OUT;
        }
        return STAY;
    }
}
