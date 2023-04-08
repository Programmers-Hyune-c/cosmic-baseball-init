package com.hyunec.cosmicbaseballinit.common;

import com.hyunec.cosmicbaseballinit.model.BattingResult;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class RandomUtils {
    private final Random random = new Random();

    public BattingResult getRandomBattingResult() {
        int value = random.nextInt(3);

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
