package com.hyunec.cosmicbaseballinit.domain.baseball.model.base;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum BaseType {
  FIRST_BASE(0), SECOND_BASE(1), THIRD_BASE(2), HOME(3);

  private final Integer basePosition;

  public static int getBaseTypeSize() {
    return BaseType.values().length;
  }
}
