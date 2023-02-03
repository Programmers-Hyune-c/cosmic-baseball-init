package com.hyunec.cosmicbaseballinit.domain.baseball.model;

import java.util.Arrays;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Batting {
  STRIKE(0), BALL(1), HIT(2);

  private final Integer value;
  private static final String VALUE_MUST_BE_SMALLER_THAN_BATTING_SIZE = "Batting으로 변환하려는 숫자 값은 반드시 Batting의 크기보다 작아야합니다.";

  public static Batting of(Integer value) {
    return Arrays.stream(Batting.values())
        .filter(e -> e.value.equals(value))
        .findFirst()
        .orElseThrow(() -> new IllegalArgumentException(VALUE_MUST_BE_SMALLER_THAN_BATTING_SIZE));
  }

  public static int getBattingSize() {
    return Batting.values().length;
  }
}
