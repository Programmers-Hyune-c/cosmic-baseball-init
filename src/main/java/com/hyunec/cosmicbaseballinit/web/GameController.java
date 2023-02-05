package com.hyunec.cosmicbaseballinit.web;

import com.hyunec.cosmicbaseballinit.domain.baseball.model.BattingResult;
import com.hyunec.cosmicbaseballinit.domain.baseball.model.PlateAppearances;
import com.hyunec.cosmicbaseballinit.domain.baseball.model.utils.generator.BattingGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class GameController {

  private final PlateAppearances plateAppearances;
  private final BattingGenerator battingGenerator;

  public GameController(
      final PlateAppearances plateAppearances,
      @Qualifier("randomBattingGenerator")
      final BattingGenerator battingGenerator) {
    this.plateAppearances = plateAppearances;
    this.battingGenerator = battingGenerator;
  }

  @GetMapping("/game/batting")
  public BattingResult batting() {
    plateAppearances.batting(battingGenerator.generator());
    log.info("### plateAppearances.result()={}", plateAppearances.result());
    return plateAppearances.result();
  }
}
