package com.hyunec.cosmicbaseballinit.domain.baseball.model.service;

import com.hyunec.cosmicbaseballinit.domain.baseball.model.Batting;
import com.hyunec.cosmicbaseballinit.domain.baseball.model.BattingResult;
import com.hyunec.cosmicbaseballinit.domain.baseball.model.PlateAppearances;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BaseballServiceImpl implements BaseballService {

  private final PlateAppearances plateAppearances;
  @Override
  public void batting() {
    plateAppearances.batting(Batting.generate());
  }

  @Override
  public BattingResult getBattingResult() {
    return plateAppearances.result();
  }
}
