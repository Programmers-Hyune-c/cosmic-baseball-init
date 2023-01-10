package com.hyunec.cosmicbaseballinit.roundGame.controller;

import com.hyunec.cosmicbaseballinit.roundGame.domain.HitterGameResultList;
import com.hyunec.cosmicbaseballinit.roundGame.domain.Round;
import com.hyunec.cosmicbaseballinit.roundGame.domain.service.RoundGameService;
import com.hyunec.cosmicbaseballinit.roundGame.domain.vo.RoundGameResult;
import com.hyunec.cosmicbaseballinit.vo.hitterGame.HittingRequestVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RoundGameImpl implements RoundGameInterface {

    private final RoundGameService roundGameService;

    // 확률 랜덤값을 클라이언트로 부터 입력 받음

    /**
     * 입력받은 확률 값을 이용하여 타구를 실시하고 타구 결과를 반환
     * @param hittingRequestVo : Hitting 결과 값에 영향을 미치는 확률을 입력으로 받음
     * @return String : 상황에 따라 PitchResult 또는 HitterResult를 String으로 변환하여 반환
     * @throws Exception
     */
    @Override
    public String hitting(HittingRequestVo hittingRequestVo) throws Exception{
        return roundGameService.hit(hittingRequestVo);
    }

    /**
     * 타구 결과에 영향을 미치는 값을 랜덤으로 설정하여 타구
     * @return String : 상황에 따라 PitchResult 또는 HitterResult를 String으로 변환하여 반환
     * @throws Exception
     */
    @Override
    public String hitting() throws Exception{
        HittingRequestVo hittingRequestVo = new HittingRequestVo(Math.random(), Math.random());
        return roundGameService.hit(hittingRequestVo);
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
