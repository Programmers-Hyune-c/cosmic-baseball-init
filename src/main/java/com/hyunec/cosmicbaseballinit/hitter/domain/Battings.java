package com.hyunec.cosmicbaseballinit.hitter.domain;

import java.util.Collections;
import java.util.List;

public class Battings {

    private final List<Batting> battings;

    public Battings(List<Batting> battings) {
        this.battings = Collections.unmodifiableList(battings);
    }

    public String battingResult() {
        final Batting lastBatting = battings.get(battings.size() - 1);

        if (isBattingResult(Batting.STRIKE, BattingsResult.OUT)) {
            return BattingsResult.OUT.name();
        }

        if (isBattingResult(Batting.BALL, BattingsResult.FOUR_BALL)) {
            return BattingsResult.FOUR_BALL.name();
        }

        return lastBatting.name();
    }

    private boolean isBattingResult(Batting batting, BattingsResult battingsResult) {
        return battingTypeCount(batting) >= battingsResult.getValue();
    }

    private int battingTypeCount(Batting battingType) {
        return battings.stream()
                .filter(batting -> batting.equals(battingType))
                .map(type -> 1)
                .reduce(0, Integer::sum);
    }

    public boolean isFinishPlaceApperances(final String battingResult) {
        return !BattingResult.isNotFinishBaseBall(battingResult);
    }

}
