package com.hyunec.cosmicbaseballinit.controller;

import com.hyunec.cosmicbaseballinit.service.lv1.Lv1GameService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class GameController {

    private final Lv1GameService gameService;

    @GetMapping("/game/start")
    public void gameStart() {
        try {
            gameService.gameStart();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
