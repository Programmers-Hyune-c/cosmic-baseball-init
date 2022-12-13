package com.hyunec.cosmicbaseballinit.vo;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;
import java.util.Map;
import java.util.Random;

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

    // 확률 세팅
    public static void settingProbability(Map<PitchResult, Double> probabilityMap){
        if(probabilityMap.size() == PitchResult.values().length){
            return;
        }
        Double sameProbability = PitchResult.calculateSameProbability();
        Arrays.stream(PitchResult.values()).forEach(pitchResult -> probabilityMap.put(pitchResult, sameProbability));
    }

    public static PitchResult pitching(Map<PitchResult, Double> probabilityMap) throws Exception {
        Double startProbability = 0D;
        Double endProbabilitty = 0D;

        Double randomDouble = Math.random();
        System.out.println(randomDouble);
        for (PitchResult pitchResult : PitchResult.values()) {
            endProbabilitty += probabilityMap.get(pitchResult);
            if (startProbability <= randomDouble && randomDouble >= endProbabilitty) {
                return pitchResult;
            }
            startProbability = endProbabilitty;
        }
        throw new Exception("batting error");
    }
}
