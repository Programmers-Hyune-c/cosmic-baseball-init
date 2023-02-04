package com.hyunec.cosmicbaseballinit.domain.baseball.model.base;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Base {
  private final List<Integer> base = new ArrayList<>(BaseType.getBaseTypeSize());

  public List<Integer> getBase() {
    return Collections.unmodifiableList(base);
  }
}
