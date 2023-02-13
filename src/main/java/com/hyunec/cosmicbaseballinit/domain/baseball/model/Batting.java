package com.hyunec.cosmicbaseballinit.domain.baseball.model;

import com.hyunec.cosmicbaseballinit.domain.baseball.model.exception.ExceptionMessage;
import lombok.Getter;

@Getter
public enum Batting {
  STRIKE(0), BALL(1), HIT(2);

  private final int value;

  Batting(final int value) {
    this.value = value;
  }

  public static Batting of(Integer index) {
    Batting[] battings = Batting.values();
    if (index > battings.length || index < 0) {
      throw new IllegalArgumentException(ExceptionMessage.VALUE_MUST_BE_SMALLER_THAN_BATTING_SIZE);
    }
    return battings[index];
  }

  public static int getBattingSize() {
    return Batting.values().length;
  }
}
