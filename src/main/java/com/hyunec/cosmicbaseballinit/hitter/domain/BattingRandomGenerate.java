package com.hyunec.cosmicbaseballinit.hitter.domain;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class BattingRandomGenerate implements RandomGenerate {

    private static final int BATTING_CASE_COUNT = 2;

    private final Random random = new Random();

    @Override
    public Batting generate() {
        final int randomValue = random.nextInt(BATTING_CASE_COUNT);

        if(Batting.STRIKE.ordinal() == randomValue) {
            return Batting.STRIKE;
        }

        if(Batting.BALL.ordinal() == randomValue) {
            return Batting.BALL;
        }

        return Batting.STRIKE;
    }
}
