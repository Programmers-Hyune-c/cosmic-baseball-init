package com.hyunec.cosmicbaseballinit.controller;

import com.hyunec.cosmicbaseballinit.service.lv1.Lv1HitterGameService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class GameController {

    private final Lv1HitterGameService gameService;

    @GetMapping("/game/setting")
    public String gameSetting(){
        gameService.setHitGameProbability();
        return "Probability setting finished";
    }

    @GetMapping("/game/hitting")
    public String hitting(){
        try {
            // TODO PitchResult에 따라 GO/OUt 판정하고, list 초기화 하도록 수정
            return gameService.hitting();
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    // TODO 현재 STRIKE, BALL, OUT 갯수 리스트 반환
    @GetMapping("/game/hittingResults")
    public void gameResult(){

    }
}
