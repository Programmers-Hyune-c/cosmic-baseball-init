package com.hyunec.cosmicbaseballinit.domain.baseball.model.service;

import com.hyunec.cosmicbaseballinit.domain.baseball.model.Batting;
import com.hyunec.cosmicbaseballinit.domain.baseball.model.BattingResult;
import com.hyunec.cosmicbaseballinit.domain.baseball.model.PlateAppearances;
import com.hyunec.cosmicbaseballinit.domain.baseball.model.utils.generator.BattingGenerator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class BaseballServiceImpl implements BaseballService{

  private final PlateAppearances plateAppearances;
  private final BattingGenerator battingGenerator;

  public BaseballServiceImpl(
      final PlateAppearances plateAppearances,
      @Qualifier("randomBattingGenerator")
      final BattingGenerator battingGenerator) {
    this.plateAppearances = plateAppearances;
    this.battingGenerator = battingGenerator;
  }

  @Override
  public void batting() {
    Batting generateValue = battingGenerator.generator();
    plateAppearances.batting(generateValue);
  }

  @Override
  public BattingResult getBattingResult() {
    return plateAppearances.result();
  }
}
