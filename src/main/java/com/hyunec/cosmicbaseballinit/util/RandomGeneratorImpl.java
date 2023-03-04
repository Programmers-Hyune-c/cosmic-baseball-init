package com.hyunec.cosmicbaseballinit.util;


import java.util.Random;

public class RandomGeneratorImpl implements RandomGenerator {

    private final Random random = new Random();

    public int getNumber(int bound) {
        return random.nextInt(bound);
    }
}
