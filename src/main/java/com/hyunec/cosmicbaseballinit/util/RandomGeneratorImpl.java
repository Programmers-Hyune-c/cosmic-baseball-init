package com.hyunec.cosmicbaseballinit.util;


import com.hyunec.cosmicbaseballinit.domain.BattingResult;
import java.util.Random;

public class RandomGeneratorImpl implements RandomGenerator {

    private final Random random = new Random();

    public int getRandomNumber() {
        return random.nextInt(BattingResult.values().length);
    }
}
