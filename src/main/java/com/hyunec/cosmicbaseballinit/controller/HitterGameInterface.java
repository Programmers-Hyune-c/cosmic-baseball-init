package com.hyunec.cosmicbaseballinit.controller;

import com.hyunec.cosmicbaseballinit.vo.hitterGame.HittingParamVo;
import com.hyunec.cosmicbaseballinit.vo.hitterGame.PitchProbabilitySettingVo;
import com.hyunec.cosmicbaseballinit.vo.hitterGame.PitchResult;

import java.util.Map;

public interface HitterGameInterface {
    public String gameSetting();
    public String gameSetting(PitchProbabilitySettingVo pitchProbabilitySettingVo);
    public String hitting(HittingParamVo hittingParamVo) throws Exception;
    public String hitting() throws Exception;
    public void initScore();
    public Map<PitchResult, Integer> hitterScore();
}
