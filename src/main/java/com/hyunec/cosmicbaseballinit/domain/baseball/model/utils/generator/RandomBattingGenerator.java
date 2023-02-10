package com.hyunec.cosmicbaseballinit.domain.baseball.model.utils.generator;

import com.hyunec.cosmicbaseballinit.domain.baseball.model.Batting;
import java.util.Random;
import org.springframework.stereotype.Component;

@Component
@RandomBattingQualifier
public class RandomBattingGenerator implements BattingGenerator {
  private static final int MAX_PROBABILITY_SIZE = 10;
  private static final int BULLSEYE_PROBABILITY = 2;

  private static Random random = new Random();

  @Override
  public Batting generator() {
    final Batting result = Batting.of(getRandomNumber(Batting.getBattingSize()));
    return result;
  }

  public int getRandomNumber(int range) {
    return random.nextInt(range);
  }
}