package com.hyunec.cosmicbaseballinit.domain.baseball.model.utils.generator;

import com.hyunec.cosmicbaseballinit.domain.baseball.model.Batting;
import java.util.Random;
import org.springframework.stereotype.Component;

@Component
@RandomBattingQualifier
public class RandomBattingGenerator implements BattingGenerator {
  private static Random random = new Random();

  @Override
  public Batting generator() {
    return Batting.of(getRandomNumber(Batting.getBattingSize()));
  }

  public int getRandomNumber(int range) {
    return random.nextInt(range);
  }
}