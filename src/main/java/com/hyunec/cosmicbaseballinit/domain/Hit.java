package com.hyunec.cosmicbaseballinit.domain;

public class Hit implements BattingResult {

    @Override
    public int call() {
        return 0;
    }

    @Override
    public String getName() {
        return this.getClass().getSimpleName();
    }
}
