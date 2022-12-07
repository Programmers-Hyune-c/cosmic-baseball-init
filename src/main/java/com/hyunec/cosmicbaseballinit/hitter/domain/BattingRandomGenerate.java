package com.hyunec.cosmicbaseballinit.hitter.domain;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class BattingRandomGenerate implements RandomGenerate {

    private static final int BATTING_CASE_COUNT = 2;

    private final Random random = new Random();

    @Override
    public int generate() {
        return random.nextInt(BATTING_CASE_COUNT) + 1;
    }
}
