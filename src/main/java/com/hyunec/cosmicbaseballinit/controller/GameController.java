package com.hyunec.cosmicbaseballinit.controller;

import com.hyunec.cosmicbaseballinit.service.Lv1HitterGameService;
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
            return gameService.hitting();
        } catch (Exception e) {
            return e.getMessage();
        }
    }

}
