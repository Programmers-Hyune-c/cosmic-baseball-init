package com.hyunec.cosmicbaseballinit.controller;

import com.hyunec.cosmicbaseballinit.vo.hitterGame.HitterResult;
import com.hyunec.cosmicbaseballinit.vo.hitterGame.HittingRequestVo;
import com.hyunec.cosmicbaseballinit.vo.hitterGame.PitchProbabilitySettingVo;
import com.hyunec.cosmicbaseballinit.vo.hitterGame.PitchResult;

import java.util.Map;

public interface HitterGameInterface {
    public String gameSetting();
    public String gameSetting(PitchProbabilitySettingVo pitchProbabilitySettingVo);
    public String hitting(HittingRequestVo hittingRequestVo) throws Exception;
    public String hitting() throws Exception;
    public void initScore();
    public Map<PitchResult, Integer> hitterScore();
    public Boolean isHitterGameEnd();
    public HitterResult getHitterGameResult();
}
