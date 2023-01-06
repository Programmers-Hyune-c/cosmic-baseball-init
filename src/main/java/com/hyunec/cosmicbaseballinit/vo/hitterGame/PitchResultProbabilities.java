package com.hyunec.cosmicbaseballinit.vo.hitterGame;

import lombok.RequiredArgsConstructor;

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

}
