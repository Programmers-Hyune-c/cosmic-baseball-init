package com.hyunec.cosmicbaseballinit.service;

import static com.hyunec.cosmicbaseballinit.service.BattingResult.BALL;
import static com.hyunec.cosmicbaseballinit.service.BattingResult.HIT;
import static com.hyunec.cosmicbaseballinit.service.BattingResult.STRIKE;

import java.util.Random;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BattingService {

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
