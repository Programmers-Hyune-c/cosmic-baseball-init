package com.hyunec.cosmicbaseballinit.controller;

import com.hyunec.cosmicbaseballinit.service.lv1.Lv1HitterGameService;
import com.hyunec.cosmicbaseballinit.vo.HitterResult;
import com.hyunec.cosmicbaseballinit.vo.PitchResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@Slf4j
public class GameController {

    private final Lv1HitterGameService gameService;

    @GetMapping("/game/setting")
    public String gameSetting(){
        gameService.setHitGameProbability();
        return "Probability setting finished"; //TODO: 하드코딩된 문자열 반환 처리하기
    }

    @GetMapping("/game/hitting")
    public String hitting() throws Exception{
        String hittingResult = gameService.hitting();
        if(gameService.isWhenScoreInit(hittingResult)){
            gameService.initGameScore();
        }
        return hittingResult;
    }

    @GetMapping("/game/hitterScore")
    public Map<PitchResult, Integer> hitterScore(){
        return gameService.getScores();
    }
}
