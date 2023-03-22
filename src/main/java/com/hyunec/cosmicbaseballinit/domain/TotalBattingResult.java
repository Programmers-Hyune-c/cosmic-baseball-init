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
    private BatterStatus batterStatus;
    private BattingResult battingResult;
    private int outCount;
    private final ScoreBoard scoreBoard = ScoreBoard.of(false, false, false);

    private TotalBattingResult(int strikeCount, int ballCount) {
        this.ballCount = ballCount;
        this.strikeCount = strikeCount;
    }

    public TotalBattingResult(int ballCount, int strikeCount, int outCount) {
        this.ballCount = ballCount;
        this.strikeCount = strikeCount;
        this.outCount = outCount;
    }

    public static TotalBattingResult of(int strikeCount, int ballCount) {
        return new TotalBattingResult(strikeCount, ballCount);
    }


    public void adjustAccordingToBattingResult(BattingResult battingResult) {
        this.battingResult = battingResult;
        this.ballCount += battingResult.getIncreaseBallCount();
        this.strikeCount += battingResult.getIncreaseStrikeCount();

        if (isGoToBase()) {
            this.batterStatus = GO_TO_BASE;
            this.scoreBoard.adjustBaseAndScore();
            resetBattingResultCount();
            return;
        }

        if (isOut()) {
            this.batterStatus = OUT;
            this.outCount++;
            resetBattingResultCount();
            return;
        }
        this.batterStatus = ON_GOING;
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
