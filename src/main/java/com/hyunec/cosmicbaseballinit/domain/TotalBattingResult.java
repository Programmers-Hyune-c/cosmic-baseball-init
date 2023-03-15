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
                resetBattingResultCount();
        }
    }

    public void judgeBatterStatus() {
        if (isGoToBase()) {
            this.batterStatus = GO_TO_BASE;
            return;
        }
        if (isOut()) {
            this.batterStatus = OUT;
        }
    }

    private void resetBattingResultCount() {
        this.strikeCount = 0;
        this.ballCount = 0;
    }

    private void increaseBallCount(BattingResult battingResult) {
        this.ballCount += battingResult.getIncreaseBallCount();
    }

    private void increaseStrikeCount(BattingResult battingResult) {
        this.strikeCount += battingResult.getIncreaseStrikeCount();
    }

    private boolean isOut() {
        return this.strikeCount >= 3;
    }

    private boolean isGoToBase() {
        return this.ballCount >= 4 || (this.strikeCount == 0 && this.ballCount == 0);
    }
}
