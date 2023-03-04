package com.hyunec.cosmicbaseballinit.util;

import java.util.Random;

public class RandomFactory implements RandomGenerator {

    @Override
    public Random createRandom() {
        return new Random();
    }
}
