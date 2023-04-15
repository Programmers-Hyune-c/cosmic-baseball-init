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
        int value = RANDOM.nextInt(5);

        switch (value) {
            case 0:
                return STRIKE;
            case 1:
                return BALL;
            case 2:
                return DOUBLE_STRIKE;
            case 3:
                return DOUBLE_BALL;
            default:
                return HIT;
        }
    }
}
