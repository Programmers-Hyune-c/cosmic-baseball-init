package com.hyunec.cosmicbaseballinit.domain;

import lombok.Getter;

@Getter
public class Ball implements BattingResult {

    private static final int BALL_COUNT = 1;

    @Override
    public int call() {
        return BALL_COUNT;
    }

    @Override
    public String getName() {
        return this.getClass().getSimpleName();
    }
}
