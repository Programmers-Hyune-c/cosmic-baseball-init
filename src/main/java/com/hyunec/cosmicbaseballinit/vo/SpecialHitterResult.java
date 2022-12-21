package com.hyunec.cosmicbaseballinit.vo;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum SpecialHitterResult {
    BULLSEYE_STRIKE(0.2, PitchResult.STRIKE),
    BULLSEYE_BALL(0.2, PitchResult.BALL),
    HOMERUN(0.2, PitchResult.HIT);

    private final Double probability;
    private final PitchResult pitchResult;

    public static SpecialHitterResult judgeSpecialHitterResultByPitchResult
            (PitchResult pitchResult, Double randomNumber) throws Exception {
        SpecialHitterResult specialHitterResult = findSpecialHitterResult(pitchResult);
        if (randomNumber < specialHitterResult.probability) {
            return specialHitterResult;
        }
        return null;
    }

    private static SpecialHitterResult findSpecialHitterResult(PitchResult pitchResult) throws Exception {
        for (SpecialHitterResult specialHitterResult : SpecialHitterResult.values()) {
            if (specialHitterResult.pitchResult.equals(pitchResult)) {
                return specialHitterResult;
            }
        }
        throw new Exception("findSpecialHitterResult error");
    }

}
