package com.hyunec.cosmicbaseballinit.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
public class BattingResultCount {

    @Setter
    private long id;
    private int ballCount;
    private int strikeCount;

    private BattingResultCount(){}

    public static BattingResultCount getInstance(){
        return new BattingResultCount();
    }

    public void addCount(BattingResult battingResult) {
        int count = battingResult.call();
        setCount(battingResult, count);
    }

    private void setCount(BattingResult battingResult, int count) {
        switch (battingResult.getName()) {
            case "Strike":
            case "DoubleStrike":
                this.strikeCount += count;
                break;
            case "Ball":
            case "DoubleBall":
                this.ballCount += count;
                break;
            default:
                this.strikeCount = count;
                this.ballCount = count;
        }
    }
}
