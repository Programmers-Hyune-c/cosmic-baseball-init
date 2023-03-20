package com.hyunec.cosmicbaseballinit.service;

import static com.hyunec.cosmicbaseballinit.domain.BattingResult.BALL;
import static com.hyunec.cosmicbaseballinit.domain.BattingResult.STRIKE;

import com.hyunec.cosmicbaseballinit.domain.BattingResult;
import java.util.Random;
import org.springframework.stereotype.Component;

@Component
public class RandomBattingResultGenerator {

    private static final Random RANDOM = new Random();
    private static final int NO_BULL_EYE_RESULT_RANGE = 5;

    public BattingResult getBattingResult() {
        BattingResult result = getRandomResult();
        return isBullEyeResult(result) ? result.toBullEyeResult() : result;
    }

    private BattingResult getRandomResult() {
        return BattingResult.values()[RANDOM.nextInt(NO_BULL_EYE_RESULT_RANGE)];
    }

    private boolean isBullEyeResult(BattingResult result) {
        boolean isStrikeOrBall = (result == BALL || result == STRIKE);
        boolean isTheSameResultOneMore = getRandomResult() == result;
        return  isStrikeOrBall && isTheSameResultOneMore;
    }

}
