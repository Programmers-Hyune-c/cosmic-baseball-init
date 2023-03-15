package com.hyunec.cosmicbaseballinit.domain;

import static com.hyunec.cosmicbaseballinit.domain.BatterStatus.GO_TO_BASE;
import static com.hyunec.cosmicbaseballinit.domain.BatterStatus.ON_GOING;
import static com.hyunec.cosmicbaseballinit.domain.BatterStatus.OUT;

import lombok.Getter;
import lombok.Setter;

@Getter
public class TotalBattingResult {

    @Setter
    private Long id;
    private int ballCount;
    private int strikeCount;
    private BatterStatus batterStatus = ON_GOING;
    @Setter
    private BattingResult battingResult;

    public void addBattingResultCount(BattingResult battingResult) {
        switch (battingResult) {
            case STRIKE:
            case DOUBLE_STRIKE:
                increaseStrikeCount(battingResult);
                return;
            case BALL:
            case DOUBLE_BALL:
                increaseBallCount(battingResult);
                return;
            default:
                resetCount(battingResult);
        }
    }

    public void judgeBatterStatus() {
        if (is4Ball()) {
            this.batterStatus = GO_TO_BASE;
            return;
        }
        if (is3Strike()) {
            this.batterStatus = OUT;
            return;
        }
        if (isHit()) {
            this.batterStatus = GO_TO_BASE;
        }
    }

    private void resetCount(BattingResult battingResult) {
        this.strikeCount = battingResult.getIncreasingCount();
        this.ballCount = battingResult.getIncreasingCount();
    }

    private void increaseBallCount(BattingResult battingResult) {
        this.ballCount += battingResult.getIncreasingCount();
    }

    private void increaseStrikeCount(BattingResult battingResult) {
        this.strikeCount += battingResult.getIncreasingCount();
    }

    private boolean isHit() {
        return this.strikeCount == 0 && this.ballCount == 0;
    }

    private boolean is3Strike() {
        return this.strikeCount >= 3;
    }

    private boolean is4Ball() {
        return this.ballCount >= 4;
    }
}
