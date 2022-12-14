package com.hyunec.cosmicbaseballinit.vo;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;
import java.util.Map;

@Getter
@RequiredArgsConstructor
@Slf4j
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

    // 확률 세팅 되었는지 체크
    private static Boolean isProbabilitySetting(Map<PitchResult, Double> probabilityMap){
        return probabilityMap.size() == PitchResult.values().length;
    }

    // 확률 세팅
    public static void settingProbability(Map<PitchResult, Double> probabilityMap){
        if(isProbabilitySetting(probabilityMap)){
            return;
        }
        Double sameProbability = PitchResult.calculateSameProbability();
        Arrays.stream(PitchResult.values()).forEach(pitchResult -> probabilityMap.put(pitchResult, sameProbability));
    }

}
