package com.hyunec.cosmicbaseballinit.domain;

import static com.hyunec.cosmicbaseballinit.domain.BatterStatus.GO_TO_BASE;
import static com.hyunec.cosmicbaseballinit.domain.BatterStatus.ON_GOING;
import static com.hyunec.cosmicbaseballinit.domain.BatterStatus.OUT;
import static com.hyunec.cosmicbaseballinit.domain.BattingResult.HIT;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AtBatResult {

    private int ballCount;
    private int strikeCount;
    private BatterStatus batterStatus;
    private BattingResult battingResult;
    private int outCount;

    public AtBatResult(int ballCount, int strikeCount, int outCount) {
        this.ballCount = ballCount;
        this.strikeCount = strikeCount;
        this.outCount = outCount;
    }

    private AtBatResult(int strikeCount, int ballCount) {
        this.ballCount = ballCount;
        this.strikeCount = strikeCount;
    }

    public static AtBatResult of(int strikeCount, int ballCount) {
        return new AtBatResult(strikeCount, ballCount);
    }

    public void adjustAccordingToBattingResult(BattingResult battingResult) {
        this.battingResult = battingResult;
        this.ballCount += battingResult.getIncreaseBallCount();
        this.strikeCount += battingResult.getIncreaseStrikeCount();

        if (isGoToBase()) {
            doGoToBaseProcess();
            return;
        }

        if (isOut()) {
            doOutProcess();
            return;
        }
        this.batterStatus = ON_GOING;
    }

    private void doGoToBaseProcess() {
        this.batterStatus = GO_TO_BASE;
        resetBattingResultCount();
    }

    private void doOutProcess() {
        this.batterStatus = OUT;
        this.outCount++;
        resetBattingResultCount();
    }

    private void resetBattingResultCount() {
        this.strikeCount = 0;
        this.ballCount = 0;
    }

    private boolean isOut() {
        return this.strikeCount >= 3;
    }

    private boolean isGoToBase() {
        return this.ballCount >= 4 || this.battingResult == HIT;
    }
}
