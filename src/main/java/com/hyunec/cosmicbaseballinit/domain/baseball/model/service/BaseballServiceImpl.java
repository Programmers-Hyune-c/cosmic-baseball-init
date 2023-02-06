package com.hyunec.cosmicbaseballinit.domain.baseball.model.service;

import com.hyunec.cosmicbaseballinit.domain.baseball.model.Batting;
import com.hyunec.cosmicbaseballinit.domain.baseball.model.BattingResult;
import com.hyunec.cosmicbaseballinit.domain.baseball.model.PlateAppearances;
import com.hyunec.cosmicbaseballinit.domain.baseball.model.dto.NewGameResponse;
import com.hyunec.cosmicbaseballinit.domain.baseball.model.exception.ExceptionMessage;
import com.hyunec.cosmicbaseballinit.domain.baseball.model.utils.generator.BattingGenerator;
import com.hyunec.cosmicbaseballinit.domain.baseball.model.utils.generator.RandomBattingQualifier;
import java.time.LocalDateTime;
import org.springframework.stereotype.Service;

@Service
public class BaseballServiceImpl implements BaseballService {

  private static final String SUCCESS_NEW_GAME = "다음 게임을 시작합니다";
  private static final boolean END_GAME = true;

  private final PlateAppearances plateAppearances = new PlateAppearances();
  private final BattingGenerator battingGenerator;

  public BaseballServiceImpl(
      @RandomBattingQualifier final BattingGenerator battingGenerator) {
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
    Integer strikeCount = plateAppearances.strikeCount();
    if (strikeCount.equals(BattingResult.OUT.getValue())) {
      return BattingResult.OUT;
    }

    Integer ballCount = plateAppearances.ballCount();
    if (ballCount.equals(BattingResult.FOUR_BALL.getValue())) {
      return BattingResult.FOUR_BALL;
    }

    if (plateAppearances.isNotPlaying())
      return BattingResult.PLAYING;

    return plateAppearances.getLastBattingResult();
  }

  @Override
  public NewGameResponse newGame() {
    if (!checkFourBallOrOut()) {
      throw new IllegalStateException(ExceptionMessage.FAILURE_PLATE_IS_EXIST_DO_NOT_PLAY_NEW_GAME);
    }

    plateAppearances.clear();
    return new NewGameResponse(SUCCESS_NEW_GAME, LocalDateTime.now());
  }

  private boolean checkFourBallOrOut() {
    BattingResult result = getBattingResult();
    if (result == BattingResult.OUT || result == BattingResult.FOUR_BALL) {
      return END_GAME;
    }
    return !END_GAME;
  }
}
