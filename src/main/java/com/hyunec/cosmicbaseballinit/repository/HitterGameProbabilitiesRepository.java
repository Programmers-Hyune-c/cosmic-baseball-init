package com.hyunec.cosmicbaseballinit.repository;

import com.hyunec.cosmicbaseballinit.vo.hitterGame.PitchProbabilitySettingVo;
import com.hyunec.cosmicbaseballinit.vo.hitterGame.PitchResult;

import java.util.Map;

public interface HitterGameProbabilitiesRepository {
    void save();
    void save(PitchProbabilitySettingVo pitchProbabilitySettingVo);
    Map<PitchResult, Double> get();
    Integer size();
}
