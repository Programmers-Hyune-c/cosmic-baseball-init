package com.hyunec.cosmicbaseballinit.batter;

import java.util.Random;
import lombok.Getter;

@Getter
public class Batting {

    private static final Random random = new Random();
    private final BattingResults result;

    private Batting(final BattingResults resultsGenerator) {
        this.result = resultsGenerator;
    }

    public static Batting of() {
        return new Batting(resultsGenerator());
    }

    private static BattingResults resultsGenerator() {
        return BattingResults.values()[random.nextInt(BattingResults.values().length)];
    }

    @Override
    public String toString() {
        return result.name();
    }
}
