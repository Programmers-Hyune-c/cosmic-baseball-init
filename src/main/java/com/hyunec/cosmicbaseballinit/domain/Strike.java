package com.hyunec.cosmicbaseballinit.domain;

import lombok.Getter;

@Getter
public class Strike implements BattingResult {

    private static final int STRIKE_COUNT = 1;
    private static final String NAME = "Strike";

    @Override
    public int call() {
        return STRIKE_COUNT;
    }

    @Override
    public String getName() {
        return NAME;
    }
}
