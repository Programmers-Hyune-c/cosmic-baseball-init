package com.hyunec.cosmicbaseballinit.domain.baseball.model;

import com.hyunec.cosmicbaseballinit.domain.baseball.model.exception.ExceptionMessage;
import java.util.Arrays;
import lombok.Getter;

@Getter
public enum Batting {
  STRIKE(0), BALL(1), HIT(2);

  private final int value;

  Batting(final int value) {
    this.value = value;
  }

  public static Batting of(Integer index) {
    return Arrays.stream(Batting.values()).filter(batting -> batting.getValue() == index)
        .findFirst()
        .orElseThrow(() -> new IllegalArgumentException(ExceptionMessage.NO_MATCH_BATTING_VALUE));
  }

  public static int getBattingSize() {
    return Batting.values().length;
  }
}
