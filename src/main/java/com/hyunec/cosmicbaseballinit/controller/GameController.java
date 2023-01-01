package com.hyunec.cosmicbaseballinit.controller;

import com.hyunec.cosmicbaseballinit.config.ReadMessageYml;
import com.hyunec.cosmicbaseballinit.service.lv1.HitterGameService;
import com.hyunec.cosmicbaseballinit.vo.PitchProbabilitySettingVo;
import com.hyunec.cosmicbaseballinit.vo.HittingParamVo;
import com.hyunec.cosmicbaseballinit.vo.PitchResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@Slf4j
public class GameController {

    private final HitterGameService gameService;
    private final ReadMessageYml readMessageYml;

    @GetMapping("/game/setting")
    public String gameSetting(){
        gameService.setHitGameProbability();
        return readMessageYml.getSettingFinished();
    }

    @PostMapping("/game/setting")
    public String gameSetting(@RequestBody PitchProbabilitySettingVo pitchProbabilitySettingVo){
        gameService.setHitGameProbability(pitchProbabilitySettingVo);
        return readMessageYml.getSettingFinished();
    }

    @PostMapping("/game/hitting")
    public String hitting(@RequestBody HittingParamVo hittingParamVo) throws Exception {
        String hittingResult = gameService.hitting(
                hittingParamVo.getPitchResultRandomDouble(),
                hittingParamVo.getHitterResultRandomDouble());
        return hittingResult;
    }

    @GetMapping("/game/hitting/random")
    public String hitting() throws Exception {
        String hittingResult = gameService.hitting(
                Math.random(),
                Math.random());
        return hittingResult;
    }

    @GetMapping("/game/initScore")
    public void initScore() {
        gameService.initGameScore();
    }

    @GetMapping("/game/hitterScore")
    public Map<PitchResult, Integer> hitterScore(){
        return gameService.getScores();
    }

}
