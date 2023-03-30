package com.hyunec.cosmicbaseballinit.util;

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
    private static final int HIT_PERCENT = 50;

    public static BattingResult get(int percentage, BattingResult target) {
        if (isInPercentage(percentage)) {
            return target;
        }
        BattingResult result = getBattingResultExcept(target);
        return isBullEyeResult(result) ? result.toBullEyeResult() : result;
    }

    public static BattingResult getOnFever(int percentage, BattingResult target) {
        if (target == HIT) {
            return getResultOnHitPercentageDouble(percentage, target);
        }

        if (isInPercentage(percentage)) {
            return target;
        }

        BattingResult result = getFeverBattingResultExcept(target);
        return isBullEyeResult(result) ? result.toBullEyeResult() : result;
    }

    private static BattingResult getResultOnHitPercentageDouble(int percentage,
        BattingResult target) {
        if (isInPercentage(percentage * 2)) {
            return target;
        }
        BattingResult result = getBattingResultExcept(target);
        return isBullEyeResult(result) ? result.toBullEyeResult() : result;
    }

    private static boolean isInPercentage(int percentage) {
        return RANDOM.nextInt(BOUND) < percentage;
    }

    private static BattingResult getBattingResultExcept(BattingResult target) {
        List<BattingResult> noTargetResultList =
            battingResultListExcept(target, BATTING_RESULT_LIST);
        return noTargetResultList.get(RANDOM.nextInt(noTargetResultList.size()));
    }

    private static BattingResult getFeverBattingResultExcept(BattingResult target) {
        List<BattingResult> noTargetResultList =
            battingResultListExcept(target, BATTING_RESULT_LIST);
        return getFeverBattingResult(noTargetResultList);
    }

    private static BattingResult getFeverBattingResult(List<BattingResult> noTargetResultList) {
        if (isInPercentage(HIT_PERCENT)) {
            return HIT;
        }
        List<BattingResult> noHitBattingResults = battingResultListExcept(HIT, noTargetResultList);
        return noHitBattingResults.get(RANDOM.nextInt(noHitBattingResults.size()));
    }

    public static List<BattingResult> battingResultListExcept(BattingResult target,
        List<BattingResult> list) {
        return list.stream().filter(e -> e != target).collect(Collectors.toList());
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
