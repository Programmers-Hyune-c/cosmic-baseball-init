package com.hyunec.cosmicbaseballinit.domain.baseball.model.service.bullseye;

import com.hyunec.cosmicbaseballinit.domain.baseball.model.Batting;
import com.hyunec.cosmicbaseballinit.domain.baseball.model.utils.generator.RandomValueGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BullseyeManager {

  private static final int MAX_PROBABILITY_SIZE = 10;
  private static final int BULLSEYE_PROBABILITY = 2;
  private final RandomValueGenerator randomValueGenerator;

  public BullseyeState confirmBullseye(Batting generatedBatting) {
    if (!generateRandomNumber(MAX_PROBABILITY_SIZE)) {
      return BullseyeState.NOT_BULLSEYE;
    }
    switch (generatedBatting) {
      case STRIKE:
        return BullseyeState.BULLSEYE_STRIKE;
      case HIT:
        return BullseyeState.HOME_RUN;
      case BALL:
        return BullseyeState.BULLSEYE_BALL;
      default:
        return BullseyeState.NOT_BULLSEYE;
    }
  }

  public boolean generateRandomNumber(int range) {
    final int randomNumber = randomValueGenerator.getRandomNumber(range);
    return randomNumber < BULLSEYE_PROBABILITY;
  }
}
