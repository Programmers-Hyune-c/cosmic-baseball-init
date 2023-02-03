package com.hyunec.cosmicbaseballinit.domain.baseball.model;

import com.hyunec.cosmicbaseballinit.domain.baseball.model.exception.ExceptionMessage;
import java.util.Arrays;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
public enum Batting {
  STRIKE, BALL, HIT;

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
