package com.hyunec.cosmicbaseballinit.domain;

public class DoubleStrike implements BattingResult {

    private static final int DOUBLE_STRIKE_COUNT = 2;

    @Override
    public int call() {
        return DOUBLE_STRIKE_COUNT;
    }

    @Override
    public String getName() {
        return this.getClass().getSimpleName();
    }
}
