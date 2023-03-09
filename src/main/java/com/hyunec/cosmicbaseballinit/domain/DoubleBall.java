package com.hyunec.cosmicbaseballinit.domain;

public class DoubleBall implements BattingResult {

    private static final int DOUBLE_BALL_COUNT = 2;
    private static final String NAME = "DoubleBall";

    @Override
    public int call() {
        return DOUBLE_BALL_COUNT;
    }

    @Override
    public String getName() {
        return NAME;
    }

}
