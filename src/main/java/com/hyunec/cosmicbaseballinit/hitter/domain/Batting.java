package com.hyunec.cosmicbaseballinit.hitter.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum Batting {
    STRIKE,
    BALL,
    HIT;

    public static Batting generate(final RandomGenerate randomGenerate) {
        return Arrays.stream(Batting.values())
                .filter(e -> e.ordinal() == randomGenerate.generate())
                .findFirst()
                .orElse(STRIKE);
    }

}
