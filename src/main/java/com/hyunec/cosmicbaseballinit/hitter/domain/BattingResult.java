package com.hyunec.cosmicbaseballinit.hitter.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum BattingResult {

    OUT(3),
    FOUR_BALL(4),

    STRIKE(null),
    BALL(null),
    HIT(null);

    private final Integer value;

    public boolean isClearable() {
        return !this.name().equals(STRIKE.name()) &&
                !this.name().equals(BALL.name());
    }

    public static BattingResult from(final Batting batting) {
        return Arrays.stream(BattingResult.values())
                .filter(battingResult -> battingResult.name().equals(batting.name()))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

}
