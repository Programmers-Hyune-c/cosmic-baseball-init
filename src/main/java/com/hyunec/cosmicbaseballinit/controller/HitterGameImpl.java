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

    // 타구 확률을 클라이언트로 부터 입력 받아서 타구
    @Override
    public String hitting(HittingRequestVo hittingRequestVo) throws Exception {
        String hittingResult = gameService.hitting(
                hittingRequestVo.getPitchResultRandomDouble(),
                hittingRequestVo.getHitterResultRandomDouble());
        return hittingResult;
    }

    // 타구 확률을 랜덤 값으로 하여 타구
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
    public Boolean isHitterGameEnd() {
        return gameService.isHitterGameEnd();
    }

    @Override
    public HitterResult getHitterGameResult() {
        return gameService.getHitterResult();
    }
}
