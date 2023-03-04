package com.hyunec.cosmicbaseballinit.acceptancetest;

import java.util.Random;

public class RandomFactory {

    private static final Random instance = new Random();

    private RandomFactory() {
    }

    public static Random getInstance() {
        return instance;
    }

}
