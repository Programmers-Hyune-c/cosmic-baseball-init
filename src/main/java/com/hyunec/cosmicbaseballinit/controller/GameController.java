package com.hyunec.cosmicbaseballinit.controller;

import com.hyunec.cosmicbaseballinit.service.OneWeekGameService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class GameController {

    @Autowired
    private OneWeekGameService gameService;

    public GameController(OneWeekGameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping("/gameStart")
    public String gameStart(){
        String gameResult = gameService.gameStarted();
        log.info("----------gameResult : " + gameResult + "----------");
        return gameResult;
    }
}
