package com.hyunec.cosmicbaseballinit.domain.baseball.model.base;

import com.hyunec.cosmicbaseballinit.domain.baseball.model.hit.HitType;
import java.util.HashMap;
import org.springframework.stereotype.Component;

@Component
public class Base {

  private final HashMap<BaseType, Boolean> base = new HashMap<>();
  private static final Boolean existPlate = true;

  public Base() {
    init();
  }

  public Boolean checkPlateExist(BaseType baseType) {
    return base.get(baseType);
  }

  public int hit(HitType hitType) {
    if (hitType == HitType.SINGLE_HIT) {
      return singleHit();
    }
    return 0;
  }

  private int singleHit() {
    int score = 0;
    if (Boolean.TRUE.equals(!base.get(BaseType.FIRST_BASE))) {
      base.put(BaseType.FIRST_BASE, existPlate);
      return score;
    }

    if (Boolean.TRUE.equals(base.get(BaseType.SECOND_BASE))) {
      if (Boolean.TRUE.equals(base.get(BaseType.THIRD_BASE))) {
        score++;
        return score;
      }
      base.put(BaseType.THIRD_BASE, existPlate);
      return score;
    }
    base.put(BaseType.SECOND_BASE, existPlate);
    return score;
  }

  private void init() {
    for (BaseType baseType : BaseType.values()) {
      base.put(baseType, !existPlate);
    }
  }
}
