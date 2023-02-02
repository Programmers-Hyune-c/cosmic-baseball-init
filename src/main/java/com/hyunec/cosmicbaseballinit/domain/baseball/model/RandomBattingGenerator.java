package com.hyunec.cosmicbaseballinit.domain.baseball.model;

import java.util.Arrays;
import java.util.Random;
import org.springframework.stereotype.Component;

@Component
public class RandomBattingGenerator implements BattingGenerator {

  private final Random random = new Random();

  @Override
  public Batting generator() {
    return Batting.convertBatting(random.nextInt(2));
  }
}












