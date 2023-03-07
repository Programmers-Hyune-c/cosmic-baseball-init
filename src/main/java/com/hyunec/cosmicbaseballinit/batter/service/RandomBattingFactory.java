package com.hyunec.cosmicbaseballinit.batter.service;

import com.hyunec.cosmicbaseballinit.batter.domain.BattingResult;
import java.util.Random;

public class RandomBattingFactory implements BattingFactory {

    private static final Random random = new Random();

    @Override
    public BattingResult generate() {
        return BattingResult.values()[random.nextInt(BattingResult.values().length)];
    }

}
