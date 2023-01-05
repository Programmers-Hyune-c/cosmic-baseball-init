package com.hyunec.cosmicbaseballinit.dao;

import com.hyunec.cosmicbaseballinit.repository.HitterGameProbabilitiesRepository;
import com.hyunec.cosmicbaseballinit.vo.hitterGame.PitchProbabilitySettingVo;
import com.hyunec.cosmicbaseballinit.vo.hitterGame.PitchResult;
import lombok.Getter;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Getter
@Repository
public class HitterGameProbabilitiesDao implements HitterGameProbabilitiesRepository {
    private Map<PitchResult, Double> pitchResultProbabilities;

    // 동일 확률로 게임 세팅
    public void save() {
        Map<PitchResult, Double> probabilityMap = new HashMap<>();
        Double sameProbability = calculateSameProbability();
        Arrays.stream(PitchResult.values()).forEach(pitchResult -> probabilityMap.put(pitchResult, sameProbability));
        pitchResultProbabilities = probabilityMap;
    }

    public void save(PitchProbabilitySettingVo pitchProbabilitySettingVo) {
        Map<PitchResult, Double> probabilityMap = new HashMap<>();
        probabilityMap.put(PitchResult.STRIKE, pitchProbabilitySettingVo.getStrikeProbabiltiy());
        probabilityMap.put(PitchResult.BALL, pitchProbabilitySettingVo.getBallProbabiltiy());
        probabilityMap.put(PitchResult.HIT, pitchProbabilitySettingVo.getHitProbabiltiy());
        validateTotalProbability(probabilityMap);
        pitchResultProbabilities = probabilityMap;
    }

    public Map<PitchResult, Double> get() {
        return pitchResultProbabilities;
    }

    public Integer size() {
        return pitchResultProbabilities.size();
    }

    private Double calculateSameProbability() {
        Double entireNumber = 1.0;
        Double numberOfHitterResult = (double) PitchResult.values().length;
        return entireNumber / numberOfHitterResult;
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
}
