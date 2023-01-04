package com.hyunec.cosmicbaseballinit.vo.hitterGame;

import lombok.Getter;

import java.util.Map;

@Getter
public class HitterGameProbabilities {
    private final Map<PitchResult, Double> pitchResultProbabilities;

    public HitterGameProbabilities(Map<PitchResult, Double> pitchResultProbabilities) {
        validateTotalProbability(pitchResultProbabilities);
        this.pitchResultProbabilities = pitchResultProbabilities;
    }

    private void validateTotalProbability(Map<PitchResult, Double> pitchResultProbabilities) {
        Double sumValue = pitchResultProbabilities.values()
                                    .stream()
                                    .mapToDouble(i -> i)
                                    .sum();
        if (!sumValue.equals(1D)) {
            throw new IllegalArgumentException("확률의 합은 1 이어야 합니다.");
        }
    }

    public Integer size() {
        return pitchResultProbabilities.size();
    }
}
