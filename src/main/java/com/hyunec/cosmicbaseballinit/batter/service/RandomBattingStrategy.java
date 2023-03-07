package com.hyunec.cosmicbaseballinit.batter.service;

import com.hyunec.cosmicbaseballinit.batter.domain.BattingResult;
import java.util.Random;

public class RandomBattingStrategy implements BattingStrategy {

    private static final Random random = new Random();

    @Override
    public BattingResult generateBatting() {
        return BattingResult.values()[random.nextInt(BattingResult.values().length)];
    }
}
