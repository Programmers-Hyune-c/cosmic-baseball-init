package com.hyunec.cosmicbaseballinit.domain.baseball.model;

import lombok.Getter;

import java.util.Arrays;
import java.util.Random;

@Getter
public enum Batting {
    STRIKE, BALL, HIT;

    private static final Random random = new Random();

    public static Batting generate() {
        return Arrays.stream(Batting.values())
                .filter(e -> e.ordinal() == random.nextInt(2) + 1)
                .findFirst()
                .orElse(STRIKE);
    }
}
