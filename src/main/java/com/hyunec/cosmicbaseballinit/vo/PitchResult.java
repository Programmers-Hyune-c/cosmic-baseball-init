package com.hyunec.cosmicbaseballinit.vo;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

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

    // 확률 세팅 되었는지 체크
    private static Boolean isProbabilitySetting(Map<PitchResult, Double> probabilityMap){
        return probabilityMap.size() == PitchResult.values().length;
    }

    public static PitchResult pitching(Map<PitchResult, Double> probabilityMap, Double randomDouble) throws Exception {
        if(!isProbabilitySetting(probabilityMap)){
            throw new IllegalArgumentException("게임 확률 세팅을 하지 않았습니다.");
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
