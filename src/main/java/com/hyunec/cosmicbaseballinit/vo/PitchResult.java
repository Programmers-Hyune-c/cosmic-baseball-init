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
    STRIKE(3),
    BALL(4),
    HIT(1);

    private final Integer value;

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

    public static PitchResult pitching(Map<PitchResult, Double> probabilityMap, Double randomDouble) throws Exception {
        if(!isProbabilitySetting(probabilityMap)){
            throw new Exception("please setting first");
        }
        Double startProbability = 0D;
        Double endProbabilitty = 0D;

        log.info(randomDouble.toString());
        for (PitchResult pitchResult : PitchResult.values()) {
            endProbabilitty += probabilityMap.get(pitchResult);
            log.info("startP{}", startProbability);
            log.info("endP{}", endProbabilitty);
            if (startProbability <= randomDouble && randomDouble <= endProbabilitty) {
                return pitchResult;
            }
            startProbability = endProbabilitty;
        }
        throw new Exception("pitching error");
    }

}
