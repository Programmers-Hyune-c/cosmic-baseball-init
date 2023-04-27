package com.hyunec.cosmicbaseballinit.model;

import lombok.Getter;

import static com.hyunec.cosmicbaseballinit.model.BatterStatus.*;

@Getter
public class PlateStatus {
    private final BatterStatus batterStatus = STAY;
    private int strikeCount = 0;
    private int ballCount = 0;
    private int hitCount = 0;

    public void updateBatterResult(BattingResult result) {
        strikeCount += result.getStrikeCount();
        ballCount += result.getBallCount();
    }

    public BatterStatus getBatterStatus() {
        if (ballCount >= 4 || hitCount == 1) {
            return ADVANCE;
        }
        if (strikeCount >= 3) {
            return OUT;
        }
        return STAY;
    }
}
