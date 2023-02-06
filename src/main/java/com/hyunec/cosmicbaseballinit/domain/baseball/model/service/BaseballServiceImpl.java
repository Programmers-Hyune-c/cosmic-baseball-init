package com.hyunec.cosmicbaseballinit.domain.baseball.model.service;

import com.hyunec.cosmicbaseballinit.domain.baseball.model.Batting;
import com.hyunec.cosmicbaseballinit.domain.baseball.model.BattingResult;
import com.hyunec.cosmicbaseballinit.domain.baseball.model.PlateAppearances;
import com.hyunec.cosmicbaseballinit.domain.baseball.model.dto.NewGameResponse;
import com.hyunec.cosmicbaseballinit.domain.baseball.model.exception.ExceptionMessage;
import com.hyunec.cosmicbaseballinit.domain.baseball.model.utils.generator.BattingGenerator;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class BaseballServiceImpl implements BaseballService {

  private static final String SUCCESS_NEW_GAME = "다음 게임을 시작합니다";
  private static final boolean END_GAME = true;

  private final PlateAppearances plateAppearances = new PlateAppearances();
  private final BattingGenerator battingGenerator;

  public BaseballServiceImpl(
      @Qualifier("randomBattingGenerator") final BattingGenerator battingGenerator) {
    this.battingGenerator = battingGenerator;
  }

  @Override
  public void batting() {
    if (checkFourBallOrOut()) {
      return;
    }
    Batting generateValue = battingGenerator.generator();
    plateAppearances.batting(generateValue);
  }

  @Override
  public BattingResult getBattingResult() {
    return plateAppearances.result();
  }

  @Override
  public NewGameResponse newGame() {
    if (checkFourBallOrOut()) {
      plateAppearances.clear();

      NewGameResponse result = new NewGameResponse();
      result.setMessage(SUCCESS_NEW_GAME);
      result.setDateTime(LocalDateTime.now());
      return result;
    }
    throw new IllegalStateException(ExceptionMessage.FAILURE_PLATE_IS_EXIST_DO_NOT_PLAY_NEW_GAME);
  }

  private boolean checkFourBallOrOut() {
    BattingResult result = plateAppearances.result();
    if (result == BattingResult.OUT || result == BattingResult.FOUR_BALL) {
      return END_GAME;
    }
    return !END_GAME;
  }
}
