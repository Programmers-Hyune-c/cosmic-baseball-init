package com.hyunec.cosmicbaseballinit.hitter.domain;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class BattingRandomGenerate implements RandomGenerate {

    private static final int BATTING_CASE_COUNT = 2;
    private static final int PERCENTAGE = 100;

    private final Random random = new Random();

    @Override
    public int generate() {
        return random.nextInt(BATTING_CASE_COUNT) + 1;
    }

    @Override
    public int percentageGenerate() {
        return random.nextInt(PERCENTAGE);
    }
}
