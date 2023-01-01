package com.hyunec.cosmicbaseballinit.vo;

import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.Optional;

@RequiredArgsConstructor
public enum HitterResult {
    STRIKE_OUT(HitterResultType.GENERAL,null, null),
    FOUR_BALL(HitterResultType.GENERAL,null, null),
    HIT(HitterResultType.GENERAL,null, null),
    BULLSEYE_STRIKE(HitterResultType.SPECIAL,0.2, PitchResult.STRIKE),
    BULLSEYE_BALL(HitterResultType.SPECIAL,0.2, PitchResult.BALL),
    HOMERUN(HitterResultType.SPECIAL,0.2, PitchResult.HIT);

    private final HitterResultType hitterResultType;
    private final Double probability;
    private final PitchResult pitchResult;

    public static Optional<HitterResult> judgeHitterResultByPitchResult(
            PitchResult lastPitchResult, Integer count, Double RandomDouble)
            throws Exception {

        if (lastPitchResult.getValue().equals(count)) {
            return Optional.of(judgeInCaseOfNotSpecial(lastPitchResult));
        }
        return judgeInCaseOfSpecial(lastPitchResult, RandomDouble);
    }

    //
    private static Optional<HitterResult> judgeInCaseOfSpecial(PitchResult pitchResult,
                                                              Double randomDouble) throws Exception {

        HitterResult hitterResult = findSpecialHitterResultByPitchResult(pitchResult);
        return Optional.of(hitterResult)
                .filter(hr -> randomDouble < hr.probability);
    }

    private static HitterResult judgeInCaseOfNotSpecial(PitchResult pitchResult) throws Exception {
        if (pitchResult == PitchResult.STRIKE) {
            return STRIKE_OUT;
        }
        if (pitchResult == PitchResult.BALL) {
            return FOUR_BALL;
        }
        if (pitchResult == PitchResult.HIT) {
            return HIT;
        }
        throw new Exception("HitterResult error");
    }

    private static HitterResult findSpecialHitterResultByPitchResult(PitchResult pitchResult) throws Exception {
        return Arrays.stream(HitterResult.values())
                    .filter(hr -> hr.hitterResultType.equals(HitterResultType.SPECIAL))
                    .filter(hr -> hr.pitchResult.equals(pitchResult))
                    .findFirst()
                    .orElseThrow(() -> new Exception("findHitterResultByPitchResult error"));
    }
}
