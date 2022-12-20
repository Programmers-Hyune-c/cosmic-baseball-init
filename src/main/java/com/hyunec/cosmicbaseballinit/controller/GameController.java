package com.hyunec.cosmicbaseballinit.controller;

import com.hyunec.cosmicbaseballinit.service.lv1.Lv1HitterGameService;
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

    @GetMapping("/game/hitting") // TODO: try-catch 대신 ControllerAdvice
    public String hitting(){
        try {
            // TODO PitchResult에 따라 GO/OUt 판정하고, list 초기화 하도록 수정
            return gameService.hitting();
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    // TODO 현재 STRIKE, BALL, OUT 갯수 리스트 반환
    @GetMapping("/game/hittingScore")
    public Map<PitchResult, Integer> hittingScore(){
        return gameService.getScores();
    }
}
