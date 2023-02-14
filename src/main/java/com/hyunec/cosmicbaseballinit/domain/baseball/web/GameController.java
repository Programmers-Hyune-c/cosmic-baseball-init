package com.hyunec.cosmicbaseballinit.domain.baseball.web;

import com.hyunec.cosmicbaseballinit.domain.baseball.model.BattingResult;
import com.hyunec.cosmicbaseballinit.domain.baseball.model.GameEntity;
import com.hyunec.cosmicbaseballinit.domain.baseball.service.GameService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
public class GameController {

    private final GameService gameService;

    @GetMapping("/game/batting")
    public BattingResult batting() {
        return gameService.batting();
    }

    @GetMapping("/game/init")
    public GameEntity initGame() {
        return gameService.initGame();
    }
}
