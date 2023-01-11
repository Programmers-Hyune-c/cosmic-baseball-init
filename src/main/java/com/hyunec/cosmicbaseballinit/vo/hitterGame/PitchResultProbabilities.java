package com.hyunec.cosmicbaseballinit.vo.hitterGame;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
public class PitchResultProbabilities {
    private final Map<PitchResult, Double> pitchResultAndProbabilities;

    public Integer size() {
        return pitchResultAndProbabilities.size();
    }

    public Double get(PitchResult pitchResult) {
        return pitchResultAndProbabilities.get(pitchResult);
    }

    public Boolean isEmpty() {
        return pitchResultAndProbabilities.size() == 0;
    }
}
