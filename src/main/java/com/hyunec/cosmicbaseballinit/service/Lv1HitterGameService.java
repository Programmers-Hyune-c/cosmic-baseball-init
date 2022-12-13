package com.hyunec.cosmicbaseballinit.service;

import com.hyunec.cosmicbaseballinit.vo.PitchResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class Lv1HitterGameService {
    public static final Map<PitchResult, Double> probabilityMap = new HashMap<>();

    public void playHittingGame(){
        PitchResult.settingProbability(probabilityMap);
    }

}
