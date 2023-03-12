package com.hyunec.cosmicbaseballinit.domain;

public class DoubleStrike implements BattingResult {

    private static final int DOUBLE_STRIKE_COUNT = 2;
    private static final String NAME = "DoubleStrike";

    @Override
    public int call() {
        return DOUBLE_STRIKE_COUNT;
    }

    @Override
    public String getName() {
        return NAME;
    }
}
