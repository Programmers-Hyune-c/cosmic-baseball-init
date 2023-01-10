package com.hyunec.cosmicbaseballinit.dao;

import com.hyunec.cosmicbaseballinit.repository.HitterGameProbabilitiesRepository;
import com.hyunec.cosmicbaseballinit.vo.hitterGame.PitchProbabilitySettingVo;
import com.hyunec.cosmicbaseballinit.vo.hitterGame.PitchResult;
import com.hyunec.cosmicbaseballinit.vo.hitterGame.PitchResultProbabilities;
import lombok.Getter;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Getter
@Repository
public class HitterGameProbabilitiesDao implements HitterGameProbabilitiesRepository {
    private PitchResultProbabilities pitchResultProbabilities = new PitchResultProbabilities(new HashMap<>());

    /**
     * 동일한 확률로 Pitch 확률을 세팅한다.
     */
    public void save() {
        Map<PitchResult, Double> probabilityMap = new HashMap<>();
        Double sameProbability = calculateSameProbability();
        Arrays.stream(PitchResult.values()).forEach(pitchResult -> probabilityMap.put(pitchResult, sameProbability));
        pitchResultProbabilities = new PitchResultProbabilities(probabilityMap);
    }

    /**
     * 입력받은 확률값으로 Pitch 확률을 세팅한다.
     * @param pitchProbabilitySettingVo : : Strike, Ball, Hit 반환에 대한 확률값
     */
    public void save(PitchProbabilitySettingVo pitchProbabilitySettingVo) {
        Map<PitchResult, Double> probabilityMap = new HashMap<>();
        probabilityMap.put(PitchResult.STRIKE, pitchProbabilitySettingVo.getStrikeProbabiltiy());
        probabilityMap.put(PitchResult.BALL, pitchProbabilitySettingVo.getBallProbabiltiy());
        probabilityMap.put(PitchResult.HIT, pitchProbabilitySettingVo.getHitProbabiltiy());
        validateTotalProbability(probabilityMap);
        pitchResultProbabilities = new PitchResultProbabilities(probabilityMap);
    }

    public PitchResultProbabilities get() {
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
