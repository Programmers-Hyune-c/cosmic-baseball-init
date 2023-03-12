package com.hyunec.cosmicbaseballinit.domain;

import static com.hyunec.cosmicbaseballinit.domain.BatterStatus.GO_TO_BASE;
import static com.hyunec.cosmicbaseballinit.domain.BatterStatus.ON_GOING;
import static com.hyunec.cosmicbaseballinit.domain.BatterStatus.OUT;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Getter;
import lombok.Setter;

@Getter
@JsonInclude(Include.NON_NULL)
public class TotalBattingResult {

    @Setter
    private Long id;
    private int ballCount;
    private int strikeCount;
    private BatterStatus batterStatus = ON_GOING;
    private BattingResult battingResult;

    private TotalBattingResult(){}

    public static TotalBattingResult getInstance(){
        return new TotalBattingResult();
    }

    public void setBattingTotalResult(BattingResult battingResult) {
        this.battingResult = battingResult;
        switch (battingResult) {
            case STRIKE:
            case DOUBLE_STRIKE:
                 setBatterStatusOnStrike(battingResult);
                 return;
            case BALL:
            case DOUBLE_BALL:
                 setBatterStatusOnBall(battingResult);
                return;
            default:
                 setBatterStatusOnHit();
        }
    }

    private void setBatterStatusOnHit() {
        this.batterStatus = GO_TO_BASE;
    }

    private void setBatterStatusOnBall(BattingResult battingResult) {
        this.ballCount += battingResult.getBattingResultCount();
        if (this.ballCount >= 4){
            this.batterStatus = GO_TO_BASE;
            return;
        }
        this.batterStatus = ON_GOING;
    }

    private void setBatterStatusOnStrike(BattingResult battingResult) {
        this.strikeCount += battingResult.getBattingResultCount();
        if (this.strikeCount >= 3){
            this.batterStatus = OUT;
            return;
        }
        this.batterStatus = ON_GOING;
    }
}
