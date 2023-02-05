package com.hyunec.cosmicbaseballinit.domain.baseball.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum BattingResult {
    STRIKE(null),
    BALL(null),
    OUT(3),
    FOUR_BALL(4),
    HIT(null),
    NOTHING(null)
    ;

    private final Integer value;

    public static BattingResult of(final Batting batting) {
        return Arrays.stream(BattingResult.values())
                .filter(e -> e.name().equals(batting.name()))
                .findFirst()
                .orElseThrow();
    }
}
