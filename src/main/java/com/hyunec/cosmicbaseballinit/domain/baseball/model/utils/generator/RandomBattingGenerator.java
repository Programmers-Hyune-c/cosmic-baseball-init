package com.hyunec.cosmicbaseballinit.domain.baseball.model.utils.generator;

import com.hyunec.cosmicbaseballinit.domain.baseball.model.Batting;
import com.hyunec.cosmicbaseballinit.domain.baseball.model.utils.radom.RandomStrategy;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("randomBattingGenerator")
public class RandomBattingGenerator implements BattingGenerator {

  private final RandomStrategy randomStrategy;

  public RandomBattingGenerator(
      @Qualifier("equalRandom")
      final RandomStrategy randomStrategy) {
    this.randomStrategy = randomStrategy;
  }

  @Override
  public Batting generator() {
    return Batting.of(randomStrategy.getNumber() % Batting.getBattingSize());
  }
}