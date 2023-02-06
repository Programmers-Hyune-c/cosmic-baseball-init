package com.hyunec.cosmicbaseballinit.domain.baseball.model.utils.generator;

import com.hyunec.cosmicbaseballinit.domain.baseball.model.Batting;
import com.hyunec.cosmicbaseballinit.domain.baseball.model.utils.generator.radom.EqualRandomQualifier;
import com.hyunec.cosmicbaseballinit.domain.baseball.model.utils.generator.radom.RandomStrategy;
import org.springframework.stereotype.Component;

@Component
@RandomBattingQualifier
public class RandomBattingGenerator implements BattingGenerator {

  private final RandomStrategy randomStrategy;

  public RandomBattingGenerator(
      @EqualRandomQualifier
      final RandomStrategy randomStrategy) {
    this.randomStrategy = randomStrategy;
  }

  @Override
  public Batting generator() {
    return Batting.of(randomStrategy.getNumber() % Batting.getBattingSize());
  }
}