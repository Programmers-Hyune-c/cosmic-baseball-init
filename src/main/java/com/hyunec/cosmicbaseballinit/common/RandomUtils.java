package com.hyunec.cosmicbaseballinit.common;

import com.hyunec.cosmicbaseballinit.model.BattingResult;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class RandomUtils {
    private static final Random RANDOM = new Random();

    private RandomUtils() {
    }

    public static BattingResult getRandomBattingResult() {
        int value = RANDOM.nextInt(3);

        switch (value) {
            case 0:
                return BattingResult.STRIKE;
            case 1:
                return BattingResult.BALL;
            default:
                return BattingResult.HIT;
        }
    }
}
