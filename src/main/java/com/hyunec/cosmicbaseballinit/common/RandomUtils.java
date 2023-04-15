package com.hyunec.cosmicbaseballinit.common;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Random;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RandomUtils {
    private static final Random RANDOM = new Random();

    public static int getRandomBattingNumber() {
        return RANDOM.nextInt(5);
    }
}
