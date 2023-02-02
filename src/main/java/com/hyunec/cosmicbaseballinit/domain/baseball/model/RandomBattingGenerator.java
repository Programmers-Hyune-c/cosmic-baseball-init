package com.hyunec.cosmicbaseballinit.domain.baseball.model;

import java.util.Random;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RandomBattingGenerator implements BattingGenerator {

  private static final int MAX_BATTING_NUMBER = 3;
  private final Random random;

  @Override
  public Batting generator() {
    return Batting.of(random.nextInt() % MAX_BATTING_NUMBER);
  }
}












