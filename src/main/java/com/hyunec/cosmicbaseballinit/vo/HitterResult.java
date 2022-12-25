package com.hyunec.cosmicbaseballinit.vo;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum HitterResult {
    STRIKE_OUT(null, null),
    FOUR_BALL(null, null),
    HIT(null, null),
    BULLSEYE_STRIKE(0.2, PitchResult.STRIKE),
    BULLSEYE_BALL(0.2, PitchResult.BALL),
    HOMERUN(0.2, PitchResult.HIT);

    private final Double probability;
    private final PitchResult pitchResult;

    public static HitterResult judgeHitterResultByPitchResult(PitchResult lastPitchResult, Integer count)
            throws Exception {
        if (lastPitchResult.getValue() == count) {
            return judgeInCaseOfNotSpecial(lastPitchResult);
        }
        return judgeInCaseOfSpecial(lastPitchResult, Math.random());
    }

    public static HitterResult judgeInCaseOfSpecial(PitchResult pitchResult,
                                                     Double randomDouble) throws Exception {
        HitterResult hitterResult = findHitterResultByPitchResult(pitchResult);
        if (randomDouble < hitterResult.probability) {
            return hitterResult;
        }
        return null;
    }

    public static HitterResult judgeInCaseOfNotSpecial(PitchResult pitchResult) throws Exception {
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

    private static HitterResult findHitterResultByPitchResult(PitchResult pitchResult) throws Exception {
        for (HitterResult hitterResult : HitterResult.values()) {
            if (hitterResult.pitchResult == null) continue;
            if (hitterResult.pitchResult.equals(pitchResult)) {
                return hitterResult;
            }
        }
        throw new Exception("findHitterResultByPitchResult error");
    }
}
