package com.hyunec.cosmicbaseballinit.domain.baseball.model.hit;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
@Getter
@RequiredArgsConstructor
public enum HitType {
  SINGLE_HIT(0), DOUBLE_HIT(1), TRIPLE_HIT(2), HOME_RUN(4);

  private final Integer baseNumber;
}
