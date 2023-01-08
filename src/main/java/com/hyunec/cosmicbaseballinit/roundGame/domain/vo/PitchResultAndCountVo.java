package com.hyunec.cosmicbaseballinit.roundGame.domain.vo;

import com.hyunec.cosmicbaseballinit.vo.hitterGame.PitchResult;
import lombok.RequiredArgsConstructor;

import java.util.Map;

@RequiredArgsConstructor
public class PitchResultAndCountVo {
    /**
     * ex)
     * Strike : 1
     * Ball : 2
     */
    private final Map<PitchResult, Integer> pitchResultInteger;

    public Map<PitchResult, Integer> get() {
        return pitchResultInteger;
    }
}
