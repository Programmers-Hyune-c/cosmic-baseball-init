package com.hyunec.cosmicbaseballinit.domain;

public class Hit implements BattingResult {

    private static final String NAME = "HIT";

    @Override
    public int call() {
        return 0;
    }

    @Override
    public String getName() {
        return NAME;
    }
}
