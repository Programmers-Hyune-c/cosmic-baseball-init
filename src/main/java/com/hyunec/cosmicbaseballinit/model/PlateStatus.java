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
        switch (result) {
            case STRIKE:
                this.strikeCount++;
                break;
            case BALL:
                this.ballCount++;
                break;
            case DOUBLE_STRIKE:
                this.strikeCount += 2;
                break;
            case DOUBLE_BALL:
                this.ballCount += 2;
                break;
            default:
                this.hitCount++;
                break;
        }
    }

    public BatterStatus getBatterStatus() {
        if (this.ballCount >= 4 || this.getHitCount() == 1) {
            return ADVANCE;
        }
        if (this.getStrikeCount() >= 3) {
            return OUT;
        }
        return STAY;
    }
}
