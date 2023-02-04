package com.hyunec.cosmicbaseballinit.domain.baseball.model.base;

import com.hyunec.cosmicbaseballinit.domain.baseball.model.hit.HitType;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Base {

  private final List<Boolean> base = new ArrayList<>(BaseType.getBaseTypeSize());

  public List<Boolean> getBase() {
    return Collections.unmodifiableList(base);
  }
}
