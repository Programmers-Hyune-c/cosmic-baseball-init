package com.hyunec.cosmicbaseballinit.domain;

import static com.hyunec.cosmicbaseballinit.domain.BattingResult.BALL;
import static com.hyunec.cosmicbaseballinit.domain.BattingResult.HIT;
import static com.hyunec.cosmicbaseballinit.domain.BattingResult.STRIKE;

import java.util.Random;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Batter {

    private final Random random;

    public BattingResult hit() {
        return getResult();
    }

    private BattingResult getResult() {
        switch (random.nextInt(3)) {
            case 0:
                return STRIKE;
            case 1:
                return HIT;
            default:
                return BALL;
        }
    }
}
