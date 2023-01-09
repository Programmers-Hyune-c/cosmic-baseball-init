package com.hyunec.cosmicbaseballinit.roundGame.controller;

import com.hyunec.cosmicbaseballinit.roundGame.domain.HitterGameResultList;
import com.hyunec.cosmicbaseballinit.roundGame.domain.Round;
import com.hyunec.cosmicbaseballinit.roundGame.domain.service.RoundGameService;
import com.hyunec.cosmicbaseballinit.roundGame.domain.vo.RoundGameResult;
import com.hyunec.cosmicbaseballinit.vo.hitterGame.HittingParamVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RoundGameInterfaceImpl implements RoundGameInterface {

    private final RoundGameService roundGameService;

    // 확률 랜덤값을 클라이언트로 부터 입력 받음
    @Override
    public String hitting(HittingParamVo hittingParamVo) throws Exception{
        return roundGameService.hit(hittingParamVo);
    }

    // 확률 랜덤값을 백엔드에서 정함
    @Override
    public String hitting() throws Exception{
        HittingParamVo hittingParamVo = new HittingParamVo(Math.random(), Math.random());
        return roundGameService.hit(hittingParamVo);
    }

    @Override
    public void initRoundGame() {
        roundGameService.init();
    }

    @Override
    public HitterGameResultList getHitterGameResults() {
        return roundGameService.getHitterGameResults();
    }

    @Override
    public RoundGameResult getRoundGameResult() {
        HitterGameResultList hitterGameResults = roundGameService.getHitterGameResults();
        Round roundGameResult = roundGameService.getScoreAndOut();
        return new RoundGameResult(hitterGameResults, roundGameResult);
    }
}
