package com.hyunec.cosmicbaseballinit.service;

import static com.hyunec.cosmicbaseballinit.service.BattingResult.BALL;
import static com.hyunec.cosmicbaseballinit.service.BattingResult.DOUBLE_STRIKE;
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
        return getBattingResult();
    }

    private BattingResult getBattingResult() {
        switch (random.nextInt(4)) {
            case 0:
                return STRIKE;
            case 1:
                return HIT;
            case 2:
                return BALL;
            default:
                return DOUBLE_STRIKE;
        }
    }
}
