package com.hyunec.cosmicbaseballinit.vo;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;
import java.util.Map;

@Getter
@RequiredArgsConstructor
public enum PitchResult {
    STRIKE(),
    BALL(),
    HIT();

    // 동일한 확률을 계산하는 함수
    private static Double calculateSameProbability() {
        Double entireNumber = 1.0;
        Double numberOfHitterResult = (double) PitchResult.values().length;
        return entireNumber / numberOfHitterResult;
    }

    public static void settingProbability(Map<PitchResult, Double> probabilityMap){
        Double sameProbability = PitchResult.calculateSameProbability();
        Arrays.stream(PitchResult.values()).forEach(pitchResult -> probabilityMap.put(pitchResult, sameProbability));
    }

}
