package com.hyunec.cosmicbaseballinit.domain.baseball.model;

import java.util.Objects;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Batting {
  STRIKE(0), BALL(1), HIT(2);

  private final Integer value;

  public static Batting convertBatting(Integer value) {
    if (Objects.equals(value, Batting.STRIKE.value))
      return Batting.STRIKE;
    else if (Objects.equals(value, Batting.BALL.value))
      return Batting.BALL;
    return HIT;
  }
}
