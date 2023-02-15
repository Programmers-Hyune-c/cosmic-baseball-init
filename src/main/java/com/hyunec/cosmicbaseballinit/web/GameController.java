package com.hyunec.cosmicbaseballinit.web;

import com.hyunec.cosmicbaseballinit.domain.baseball.model.BattingResult;
import com.hyunec.cosmicbaseballinit.domain.baseball.model.dto.NewGameResponse;
import com.hyunec.cosmicbaseballinit.domain.baseball.model.service.BaseballManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
public class GameController {

  private final BaseballManager baseballService;

  @GetMapping("/game/batting")
  public BattingResult batting() {
    baseballService.batting();
    log.info("### plateAppearances.result()={}", baseballService.getBattingResult());
    return baseballService.getBattingResult();
  }

  @GetMapping("/game/new-game")
  public NewGameResponse newGame() {
    return baseballService.newGame();
  }
}
