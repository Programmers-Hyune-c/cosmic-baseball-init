package com.hyunec.cosmicbaseballinit.hitter.domain;

import lombok.RequiredArgsConstructor;

import java.util.Set;

@RequiredArgsConstructor
public enum BattingResult {

    STRIKE,
    BALL,
    HIT;

    private static final Set<String> CONTINUE_PLAY = Set.of(STRIKE.name(), BALL.name());

    public static BattingResult from(final Batting batting) {
        return BattingResult.valueOf(batting.name());
    }

    public static boolean isNotFinishBaseBall(final String battingResult) {
        return CONTINUE_PLAY.contains(battingResult);
    }
}
