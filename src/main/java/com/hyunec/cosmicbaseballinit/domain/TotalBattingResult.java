package com.hyunec.cosmicbaseballinit.domain;

import static com.hyunec.cosmicbaseballinit.domain.BatterStatus.GO_TO_BASE;
import static com.hyunec.cosmicbaseballinit.domain.BatterStatus.ON_GOING;
import static com.hyunec.cosmicbaseballinit.domain.BatterStatus.OUT;
import static com.hyunec.cosmicbaseballinit.domain.BattingResult.HIT;

import lombok.Getter;
import lombok.Setter;

@Getter
public class TotalBattingResult {

    @Setter
    private Long id;
    private int ballCount;
    private int strikeCount;
    private BatterStatus batterStatus = ON_GOING;
    private BattingResult battingResult;

    private TotalBattingResult(int strikeCount, int ballCount) {
        this.ballCount = ballCount;
        this.strikeCount = strikeCount;
    }

    public static TotalBattingResult of(int strikeCount, int ballCount) {
        return new TotalBattingResult(strikeCount, ballCount);
    }

    public void adjustAccordingToBattingResult(BattingResult battingResult) {
        this.battingResult = battingResult;
        this.ballCount += battingResult.getIncreaseBallCount();
        this.strikeCount += battingResult.getIncreaseStrikeCount();
        setBatterStatus();
    }

    private void setBatterStatus() {
        if (isGoToBase()) {
            this.batterStatus = GO_TO_BASE;
            return;
        }
        if (isOut()) {
            this.batterStatus = OUT;
        }
    }

    private boolean isOut() {
        return this.strikeCount >= 3;
    }

    private boolean isGoToBase() {
        return this.ballCount >= 4 || this.battingResult == HIT;
    }
}
