package com.hyunec.cosmicbaseballinit.domain.baseball.model;

import static com.hyunec.cosmicbaseballinit.domain.baseball.model.Batting.BALL;
import static com.hyunec.cosmicbaseballinit.domain.baseball.model.Batting.STRIKE;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class PlateAppearances {

    private final List<Batting> battings = new ArrayList<>();

    public void batting(final Batting batting) {
        battings.add(batting);
    }

    public Integer strikeCount() {
        return Math.toIntExact(battings.stream().filter(STRIKE::equals).count());
    }

    public Integer ballCount() {
        return Math.toIntExact(battings.stream().filter(BALL::equals).count());
    }

    public BattingResult getLastBattingResult() {
        return BattingResult.of(battings.get(battings.size() - 1));
    }

    public boolean isNotPlaying() {
        return battings.isEmpty();
    }

    public void clear() {
        battings.clear();
    }

    public boolean isBullseyeStrike() {
        if (battings.isEmpty())
            return false;

        if (battings.get(battings.size() - 1) == Batting.BULLSEYE_STRIKE)
            return true;
        return false;
    }
}
