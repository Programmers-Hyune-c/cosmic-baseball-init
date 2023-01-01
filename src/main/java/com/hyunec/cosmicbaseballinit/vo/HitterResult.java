package com.hyunec.cosmicbaseballinit.vo;

import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.Optional;

@RequiredArgsConstructor
public enum HitterResult {
    STRIKE_OUT(HitterResultType.GENERAL,null, PitchResult.STRIKE),
    FOUR_BALL(HitterResultType.GENERAL,null, PitchResult.BALL),
    HIT(HitterResultType.GENERAL,null, PitchResult.HIT),
    BULLSEYE_STRIKE(HitterResultType.SPECIAL,0.2, PitchResult.STRIKE),
    BULLSEYE_BALL(HitterResultType.SPECIAL,0.2, PitchResult.BALL),
    HOMERUN(HitterResultType.SPECIAL,0.2, PitchResult.HIT);

    private final HitterResultType hitterResultType;
    private final Double probability;
    private final PitchResult pitchResult;

    public static Optional<HitterResult> judgeHitterResultByPitchResult(
            PitchResult lastPitchResult, Integer count, Double RandomDouble)
            throws Exception {
        // specialResult를 우선적으로 반환
        Optional<HitterResult> specialHitterResult = judgeInCaseOfSpecial(lastPitchResult, RandomDouble);
        if (specialHitterResult.isPresent()) {
            return specialHitterResult;
        }
        return judgeInCaseOfNotSpecial(lastPitchResult, count);
    }

    //
    private static Optional<HitterResult> judgeInCaseOfSpecial(PitchResult pitchResult,
                                                              Double randomDouble) throws Exception {

        HitterResult hitterResult = findSpecialHitterResultByPitchResult(pitchResult);
        return Optional.of(hitterResult)
                .filter(hr -> randomDouble < hr.probability);
    }

    private static Optional<HitterResult> judgeInCaseOfNotSpecial(PitchResult pitchResult, Integer count) throws Exception {
        if (!pitchResult.getValue().equals(count)){
            return Optional.empty();
        }
        return Arrays.stream(HitterResult.values())
                    .filter(hr -> hr.hitterResultType.equals(HitterResultType.GENERAL))
                    .filter(hr -> hr.pitchResult.equals(pitchResult))
                    .findFirst();
    }

    private static HitterResult findSpecialHitterResultByPitchResult(PitchResult pitchResult) throws Exception {
        return Arrays.stream(HitterResult.values())
                    .filter(hr -> hr.hitterResultType.equals(HitterResultType.SPECIAL))
                    .filter(hr -> hr.pitchResult.equals(pitchResult))
                    .findFirst()
                    .orElseThrow(() -> new Exception("findHitterResultByPitchResult error"));
    }
}
