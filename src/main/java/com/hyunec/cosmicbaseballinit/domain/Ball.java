package com.hyunec.cosmicbaseballinit.domain;

import lombok.Getter;

@Getter
public class Ball implements BattingResult {

    private static final int BALL_COUNT = 1;
    private static final String NAME = "Ball";

    @Override
    public int call() {
        return BALL_COUNT;
    }

    @Override
    public String getName() {
        return NAME;
    }
}
