package com.hyunec.cosmicbaseballinit.controller;

import com.hyunec.cosmicbaseballinit.service.OneLevelGameService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class GameController {

    private final OneLevelGameService gameService;

    @GetMapping("/game/start")
    public String gameStart(){
        String gameResult = gameService.gameStarted();
        log.info("----------gameResult : " + gameResult + "----------");
        return gameResult;
    }
}
