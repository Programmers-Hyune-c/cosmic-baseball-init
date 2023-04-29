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
        if (result.equals(BattingResult.HIT)) {
            hitCount++;
        }
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

    public Boolean checkInitialStatus() {
        if (strikeCount == 0 && ballCount == 0 && hitCount == 0 && batterStatus.equals(STAY)) {
            return true;
        }
        return false;
    }
}
