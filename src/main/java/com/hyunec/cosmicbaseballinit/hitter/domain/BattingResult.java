package com.hyunec.cosmicbaseballinit.hitter.domain;

import com.hyunec.cosmicbaseballinit.hitter.exception.NotFoundBattingResult;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum BattingResult {

    OUT(3, null),
    FOUR_BALL(4, null),

    BULLSEYE_STRIKE(20, null),
    BULLSEYE_BALL(20, null),
    HOMERUN( 20, null),

    STRIKE(null, BattingResult.BULLSEYE_STRIKE),
    BALL(null, BattingResult.BULLSEYE_BALL),
    HIT(null, BattingResult.HOMERUN);

    private final Integer value;
    private final BattingResult nextResult;

    public boolean isClear() {
        return !this.name().equals(STRIKE.name()) &&
                !this.name().equals(BALL.name());
    }

    public static BattingResult from(final Batting batting) {
        return Arrays.stream(BattingResult.values())
                .filter(battingResult -> battingResult.name().equals(batting.name()))
                .findFirst()
                .orElseThrow(NotFoundBattingResult::new);
    }

}
