package com.hyunec.cosmicbaseballinit.domain.baseball.model.service;

import com.hyunec.cosmicbaseballinit.domain.baseball.model.BattingResult;
import com.hyunec.cosmicbaseballinit.domain.baseball.model.dto.NewGameResponse;

public interface BaseballService {
  void batting();
  BattingResult getBattingResult();
  NewGameResponse newGame();
  boolean generateRandomNumber(int range);
  int getScore();
}
