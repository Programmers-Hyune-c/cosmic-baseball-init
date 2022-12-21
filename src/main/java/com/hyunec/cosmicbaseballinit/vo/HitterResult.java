package com.hyunec.cosmicbaseballinit.vo;


public enum HitterResult {
    STRIKE_OUT,
    FOUR_BALL,
    HIT;

    public static HitterResult judgeHitterResultByPitchResult(PitchResult lastPitchResult) throws Exception{
        if (lastPitchResult == PitchResult.STRIKE) {
            return STRIKE_OUT;
        }
        if (lastPitchResult == PitchResult.BALL) {
            return FOUR_BALL;
        }
        if (lastPitchResult == PitchResult.HIT) {
            return HIT;
        }
        throw new Exception("HitterResult error");
    }
}
