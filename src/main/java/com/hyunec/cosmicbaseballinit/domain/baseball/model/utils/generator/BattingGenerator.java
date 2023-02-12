package com.hyunec.cosmicbaseballinit.domain.baseball.model.utils.generator;

import com.hyunec.cosmicbaseballinit.domain.baseball.model.Batting;

public interface BattingGenerator {
  Batting generator();
  int getRandomNumber(int range);

  int getBullEyesNumber();

  Batting checkBullEyes(Batting result);
}
