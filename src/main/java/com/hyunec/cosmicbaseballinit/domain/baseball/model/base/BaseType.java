package com.hyunec.cosmicbaseballinit.domain.baseball.model.base;

public enum BaseType {
  FIRST_BASE, SECOND_BASE, THIRD_BASE, HOME;

  public static int getBaseTypeSize() {
    return BaseType.values().length;
  }
}
