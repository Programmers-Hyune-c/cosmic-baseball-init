package com.hyunec.cosmicbaseballinit.roundGame.controller;

import com.hyunec.cosmicbaseballinit.vo.hitterGame.HittingParamVo;

public interface RoundGameInterface {

    public String hitting(HittingParamVo hittingParamVo) throws Exception;
    public String hitting() throws Exception;
    public void initRoundGame();
    public void getScore();
}
