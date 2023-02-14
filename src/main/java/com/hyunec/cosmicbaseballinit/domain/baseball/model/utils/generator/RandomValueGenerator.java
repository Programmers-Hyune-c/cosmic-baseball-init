package com.hyunec.cosmicbaseballinit.domain.baseball.model.utils.generator;

import com.hyunec.cosmicbaseballinit.domain.baseball.model.Batting;
import java.util.Random;
import org.springframework.stereotype.Component;

@Component
public class RandomValueGenerator {

  private static final Random random = new Random();

  public Batting generator() {
    return Batting.of(getRandomNumber(Batting.getBattingSize()));
  }

  public int getRandomNumber(int range) {
    return random.nextInt(range);
  }
}