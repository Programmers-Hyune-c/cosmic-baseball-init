package com.hyunec.cosmicbaseballinit.domain;

import lombok.Getter;

@Getter
public class Strike implements BattingResult {

    private static final int STRIKE_COUNT = 1;

    @Override
    public int call() {
        return STRIKE_COUNT;
    }

    @Override
    public String getName() {
        return this.getClass().getSimpleName();
    }
}
