package com.hyunec.cosmicbaseballinit.roundGame.controller;

import com.hyunec.cosmicbaseballinit.roundGame.domain.HitterGameResultList;
import com.hyunec.cosmicbaseballinit.roundGame.domain.vo.RoundGameResult;
import com.hyunec.cosmicbaseballinit.vo.hitterGame.HittingParamVo;
import org.springframework.stereotype.Component;

@Component
public interface RoundGameInterface {

    public String hitting(HittingParamVo hittingParamVo) throws Exception;
    public String hitting() throws Exception;
    public void initRoundGame();
    public HitterGameResultList getHitterGameResults();
    public RoundGameResult getRoundGameResult();
}
