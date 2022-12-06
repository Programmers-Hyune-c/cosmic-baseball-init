package com.hyunec.cosmicbaseballinit.hitter.domain;

import java.util.Collections;
import java.util.List;

public class Battings {

    private final List<Batting> battings;
    private final RandomGenerate randomGenerate;

    public Battings(List<Batting> battings, RandomGenerate randomGenerate) {
        this.battings = Collections.unmodifiableList(battings);
        this.randomGenerate = randomGenerate;
    }

    public BattingResult battingResult(final Batting lastBatting) {
        if(isBattingResult(Batting.STRIKE, BattingResult.OUT)) {
            return BattingResult.OUT;
        }

        if(isBattingResult(Batting.BALL, BattingResult.FOUR_BALL)) {
            return BattingResult.FOUR_BALL;
        }

        BattingResult battingResult = BattingResult.from(lastBatting);

        if(battingResult.getNextResult().getValue() > randomGenerate.percentageGenerate()) {
            return battingResult.getNextResult();
        }
        return battingResult;
    }

    private boolean isBattingResult(Batting batting, BattingResult battingResult) {
        return battingTypeCount(batting) >= battingResult.getValue();
    }

    private int battingTypeCount(Batting battingType) {
        return battings.stream()
                .filter(batting -> batting.equals(battingType))
                .map(type -> 1)
                .reduce(0, Integer::sum);
    }

}
