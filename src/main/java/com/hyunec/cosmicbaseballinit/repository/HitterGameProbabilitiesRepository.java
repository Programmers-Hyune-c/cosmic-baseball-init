package com.hyunec.cosmicbaseballinit.repository;

import com.hyunec.cosmicbaseballinit.vo.hitterGame.PitchProbabilitySettingVo;
import com.hyunec.cosmicbaseballinit.vo.hitterGame.PitchResult;
import com.hyunec.cosmicbaseballinit.vo.hitterGame.PitchResultProbabilities;

import java.util.Map;

public interface HitterGameProbabilitiesRepository {
    void save();
    void save(PitchProbabilitySettingVo pitchProbabilitySettingVo);
    PitchResultProbabilities get();
    Integer size();
}
