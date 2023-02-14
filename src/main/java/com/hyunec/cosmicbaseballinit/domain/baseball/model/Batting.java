package com.hyunec.cosmicbaseballinit.domain.baseball.model;

import java.util.Random;
import lombok.Getter;

@Getter
public enum Batting {
    STRIKE, BALL, HIT;

    private static final Random random = new Random();

    public static Batting generate() {
        int pick = random.nextInt(Batting.values().length);
        return Batting.values()[pick];
    }
}
