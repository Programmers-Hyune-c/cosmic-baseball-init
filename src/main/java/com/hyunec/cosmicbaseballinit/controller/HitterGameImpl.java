package com.hyunec.cosmicbaseballinit.controller;

import com.hyunec.cosmicbaseballinit.config.ReadMessageYml;
import com.hyunec.cosmicbaseballinit.service.lv1.HitterGameService;
import com.hyunec.cosmicbaseballinit.vo.hitterGame.HitterResult;
import com.hyunec.cosmicbaseballinit.vo.hitterGame.PitchProbabilitySettingVo;
import com.hyunec.cosmicbaseballinit.vo.hitterGame.HittingRequestVo;
import com.hyunec.cosmicbaseballinit.vo.hitterGame.PitchResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RequiredArgsConstructor
@Slf4j
public class HitterGameImpl implements HitterGameInterface{

    private final HitterGameService gameService;
    private final ReadMessageYml readMessageYml;

    @Override
    public String gameSetting(){
        gameService.setHitGameProbability();
        return readMessageYml.getSettingFinished();
    }

    @Override
    public String gameSetting(PitchProbabilitySettingVo pitchProbabilitySettingVo){
        gameService.setHitGameProbability(pitchProbabilitySettingVo);
        return readMessageYml.getSettingFinished();
    }

    /**
     * 입력받은 확률 값을 이용하여 타구를 실시하고 타구 결과를 반환
     * @param hittingRequestVo : Hitting 결과 값에 영향을 미치는 확률을 입력으로 받음
     * @return String : 상황에 따라 PitchResult 또는 HitterResult를 String으로 변환하여 반환
     * @throws Exception
     */
    @Override
    public String hitting(HittingRequestVo hittingRequestVo) throws Exception {
        String hittingResult = gameService.hitting(
                hittingRequestVo.getPitchResultRandomDouble(),
                hittingRequestVo.getHitterResultRandomDouble());
        return hittingResult;
    }

    /**
     * 타구 결과에 영향을 미치는 값을 랜덤으로 설정하여 타구
     * @return String : 상황에 따라 PitchResult 또는 HitterResult를 String으로 변환하여 반환
     * @throws Exception
     */
    @Override
    public String hitting() throws Exception {
        String hittingResult = gameService.hitting(
                Math.random(),
                Math.random());
        return hittingResult;
    }

    @Override
    public void initScore() {
        gameService.initGameScore();
    }

    @Override
    public Map<PitchResult, Integer> hitterScore(){
        return gameService.getScores();
    }

    @Override
    public Boolean isEnd() {
        return gameService.isHitterGameEnd();
    }

    @Override
    public HitterResult getHitterGameResult() {
        return gameService.getHitterResult();
    }
}
