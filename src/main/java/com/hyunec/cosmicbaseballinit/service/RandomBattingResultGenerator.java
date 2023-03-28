package com.hyunec.cosmicbaseballinit.service;

import static com.hyunec.cosmicbaseballinit.domain.BattingResult.BALL;
import static com.hyunec.cosmicbaseballinit.domain.BattingResult.DOUBLE_BALL;
import static com.hyunec.cosmicbaseballinit.domain.BattingResult.DOUBLE_STRIKE;
import static com.hyunec.cosmicbaseballinit.domain.BattingResult.HIT;
import static com.hyunec.cosmicbaseballinit.domain.BattingResult.STRIKE;
import static lombok.AccessLevel.PRIVATE;

import com.hyunec.cosmicbaseballinit.domain.BattingResult;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = PRIVATE)
public class RandomBattingResultGenerator {

    private static final Random RANDOM = new Random();
    private static final List<BattingResult> BATTING_RESULT_LIST =
        List.of(STRIKE, BALL, HIT, DOUBLE_STRIKE, DOUBLE_BALL);
    private static final int BOUND = 100;
    private static final int HIT_PERCENT = 40;

    public static BattingResult get(int percentage, BattingResult target) {
        if (isInPercentage(percentage)) {
            return target;
        }
        BattingResult result = getBattingResultExcept(target);
        return isBullEyeResult(result) ? result.toBullEyeResult() : result;
    }

    public static BattingResult getResultOnFever() {

        if(isInPercentage(HIT_PERCENT)){
           return HIT;
        }
        BattingResult result = getBattingResultExcept(HIT);
        return isBullEyeResult(result) ? result.toBullEyeResult() : result;
    }

    private static boolean isInPercentage(int percentage) {
        return RANDOM.nextInt(BOUND) < percentage;
    }

    private static BattingResult getBattingResultExcept(BattingResult target) {
        List<BattingResult> noTargetResultList = battingResultListExcept(target);
        return noTargetResultList.get(RANDOM.nextInt(noTargetResultList.size()));
    }

    public static List<BattingResult> battingResultListExcept(BattingResult target) {
        return BATTING_RESULT_LIST.stream().filter(e -> e != target).collect(Collectors.toList());
    }

    private static boolean isBullEyeResult(BattingResult result) {
        boolean isStrikeOrBall = (result == BALL || result == STRIKE);
        boolean isTheSameResultOneMore = (getRandomResult() == result);
        return isStrikeOrBall && isTheSameResultOneMore;
    }

    private static BattingResult getRandomResult() {
        return BattingResult.values()[RANDOM.nextInt(BATTING_RESULT_LIST.size())];
    }


}
