package com.hyunec.cosmicbaseballinit.common;

import com.hyunec.cosmicbaseballinit.model.BattingResult;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Random;

import static com.hyunec.cosmicbaseballinit.model.BattingResult.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RandomUtils {
    private static final Random RANDOM = new Random();

    public static BattingResult getRandomBattingResult() {
        int value = RANDOM.nextInt(3);

        switch (value) {
            case 0:
                return STRIKE;
            case 1:
                return BALL;
            default:
                return HIT;
        }
    }
}
