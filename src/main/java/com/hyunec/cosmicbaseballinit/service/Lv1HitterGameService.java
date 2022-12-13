package com.hyunec.cosmicbaseballinit.service;

import com.hyunec.cosmicbaseballinit.vo.PitchResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class Lv1HitterGameService {
    public final Map<PitchResult, Double> probabilityMap = new HashMap<>();
    public final List<PitchResult> hittingResult = new ArrayList<>();

    // 확률 세팅
    public void setHitGameProbability(){
        PitchResult.settingProbability(probabilityMap);
    }

    public String hitting() throws Exception{
        PitchResult pitchResult = PitchResult.pitching(probabilityMap);
        hittingResult.add(pitchResult);
        return pitchResult.name();
    }
}
