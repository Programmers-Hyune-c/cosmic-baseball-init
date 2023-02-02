package com.hyunec.cosmicbaseballinit.domain.baseball.model;

import java.util.Arrays;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Batting {
  STRIKE(0), BALL(1), HIT(2);

  private final Integer value;

  public static Batting of(Integer value) {
    return Arrays.stream(Batting.values())
        .filter(e -> e.value.equals(value))
        .findFirst()
        .orElseThrow();
  }
}