package com.hyunec.cosmicbaseballinit.domain.baseball.model;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RandomBattingGenerator implements BattingGenerator {

  private final RandomStrategy random;

  @Override
  public Batting generator() {
    return Batting.of(random.getNumber() % Batting.getBattingSize());
  }
}