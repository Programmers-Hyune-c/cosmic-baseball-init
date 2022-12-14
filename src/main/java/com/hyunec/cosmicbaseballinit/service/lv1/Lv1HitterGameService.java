package com.hyunec.cosmicbaseballinit.service.lv1;

import com.hyunec.cosmicbaseballinit.vo.HitterResult;
import com.hyunec.cosmicbaseballinit.vo.IntHolder;
import com.hyunec.cosmicbaseballinit.vo.PitchResult;
import com.hyunec.cosmicbaseballinit.vo.ProbabilityType;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Slf4j
public class Lv1HitterGameService {
    public final Map<PitchResult, Double> probabilityMap = new HashMap<>();
    public final List<PitchResult> hittingResult = new ArrayList<>();

    // 확률 세팅
    public void setHitGameProbability(){
        PitchResult.settingProbability(probabilityMap);
    }


}
