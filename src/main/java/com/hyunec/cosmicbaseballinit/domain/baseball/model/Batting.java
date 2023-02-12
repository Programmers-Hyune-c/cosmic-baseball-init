package com.hyunec.cosmicbaseballinit.domain.baseball.model;

import com.hyunec.cosmicbaseballinit.domain.baseball.model.exception.ExceptionMessage;
import lombok.Getter;

@Getter
public enum Batting {
  STRIKE, BALL, HIT, BULLSEYE_STRIKE;

  private final static int BATTING_SIZE = 3;

  public static Batting of(Integer index) {
    Batting[] battings = Batting.values();
    if (index > battings.length || index < 0) {
      throw new IllegalArgumentException(ExceptionMessage.VALUE_MUST_BE_SMALLER_THAN_BATTING_SIZE);
    }
    return battings[index];
  }

  public static int getBattingSize() {
    return BATTING_SIZE;
  }
}
