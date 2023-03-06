package com.hyunec.cosmicbaseballinit.batting;

import java.util.Random;

public class RandomBattingStrategy implements BattingStrategy {

    private static final Random random = new Random();

    @Override
    public BattingResults generateResult() {
        return BattingResults.values()[random.nextInt(BattingResults.values().length)];
    }
}
