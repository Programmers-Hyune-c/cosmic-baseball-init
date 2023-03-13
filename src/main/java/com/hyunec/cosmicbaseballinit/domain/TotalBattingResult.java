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
                this.strikeCount += 1;
                return;
            case DOUBLE_STRIKE:
                this.strikeCount += 2;
                return;
            case BALL:
                this.ballCount += 1;
                return;
            case DOUBLE_BALL:
                this.ballCount += 2;
                break;
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
       if (isHit()){
           this.batterStatus = GO_TO_BASE;
       }
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
