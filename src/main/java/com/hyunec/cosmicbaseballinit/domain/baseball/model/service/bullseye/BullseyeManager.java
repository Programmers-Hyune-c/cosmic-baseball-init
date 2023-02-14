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
  private static final boolean BULLSEYE = true;
  private final RandomValueGenerator randomValueGenerator;
  public BullseyeState confirmBullseye(Batting generatedBatting) {
    if (!generateRandomNumber(MAX_PROBABILITY_SIZE)) {
      return BullseyeState.NOT_BULLSEYE;
    }
    if (generatedBatting == Batting.STRIKE) {
      return BullseyeState.BULLSEYE_STRIKE;
    }
    if (generatedBatting == Batting.BALL) {
      return BullseyeState.BULLSEYE_BALL;
    }
    if (generatedBatting == Batting.HIT) {
      return BullseyeState.HOME_RUN;
    }
    return BullseyeState.NOT_BULLSEYE;
  }

  public boolean generateRandomNumber(int range) {
      final int randomNumber = randomValueGenerator.getRandomNumber(range);
      if (randomNumber < BULLSEYE_PROBABILITY) {
        return BULLSEYE;
      }
      return !BULLSEYE;
  }
}
