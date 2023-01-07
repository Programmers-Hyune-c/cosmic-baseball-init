package com.hyunec.cosmicbaseballinit.controller;

import com.hyunec.cosmicbaseballinit.config.ReadMessageYml;
import com.hyunec.cosmicbaseballinit.service.lv1.HitterGameService;
import com.hyunec.cosmicbaseballinit.vo.hitterGame.PitchProbabilitySettingVo;
import com.hyunec.cosmicbaseballinit.vo.hitterGame.HittingParamVo;
import com.hyunec.cosmicbaseballinit.vo.hitterGame.PitchResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RequiredArgsConstructor
@Slf4j
public class HitterGameInterfaceImpl implements HitterGameInterface{

    private final HitterGameService gameService;
    private final ReadMessageYml readMessageYml;

    public String gameSetting(){
        gameService.setHitGameProbability();
        return readMessageYml.getSettingFinished();
    }

    public String gameSetting(PitchProbabilitySettingVo pitchProbabilitySettingVo){
        gameService.setHitGameProbability(pitchProbabilitySettingVo);
        return readMessageYml.getSettingFinished();
    }

    // 타구 확률을 클라이언트로 부터 입력 받아서 타구
    public String hitting(HittingParamVo hittingParamVo) throws Exception {
        String hittingResult = gameService.hitting(
                hittingParamVo.getPitchResultRandomDouble(),
                hittingParamVo.getHitterResultRandomDouble());
        return hittingResult;
    }

    // 타구 확률을 랜덤 값으로 하여 타구
    public String hitting() throws Exception {
        String hittingResult = gameService.hitting(
                Math.random(),
                Math.random());
        return hittingResult;
    }

    public void initScore() {
        gameService.initGameScore();
    }

    public Map<PitchResult, Integer> hitterScore(){
        return gameService.getScores();
    }

    @Override
    public Boolean isHitterGameEnd() {
        return gameService.isHitterGameEnd();
    }
}
