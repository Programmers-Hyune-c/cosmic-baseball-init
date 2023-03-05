package com.hyunec.cosmicbaseballinit.util;


import java.util.Random;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RandomGeneratorImpl implements RandomGenerator {

    private final Random random;

    public int getNumber(int bound) {
        return random.nextInt(bound);
    }
}
