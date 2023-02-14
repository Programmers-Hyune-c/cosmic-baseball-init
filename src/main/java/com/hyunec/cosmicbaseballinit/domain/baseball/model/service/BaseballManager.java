package com.hyunec.cosmicbaseballinit.domain.baseball.model.service;

import com.hyunec.cosmicbaseballinit.domain.baseball.model.Batting;
import com.hyunec.cosmicbaseballinit.domain.baseball.model.BattingResult;
import com.hyunec.cosmicbaseballinit.domain.baseball.model.PlateAppearances;
import com.hyunec.cosmicbaseballinit.domain.baseball.model.dto.NewGameResponse;
import com.hyunec.cosmicbaseballinit.domain.baseball.model.exception.ExceptionMessage;
import com.hyunec.cosmicbaseballinit.domain.baseball.model.service.bullseye.BullseyeManager;
import com.hyunec.cosmicbaseballinit.domain.baseball.model.service.bullseye.BullseyeState;
import com.hyunec.cosmicbaseballinit.domain.baseball.model.utils.generator.RandomValueGenerator;
import com.hyunec.cosmicbaseballinit.domain.baseball.model.utils.score.Score;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BaseballManager{

  private static final String SUCCESS_NEW_GAME = "다음 게임을 시작합니다";
  private static final boolean END_GAME = true;
  private final PlateAppearances plateAppearances = new PlateAppearances();
  private final Score score = new Score();
  private final BullseyeManager bullseyeManager;
  private final RandomValueGenerator randomValueGenerator;

  public void batting() {
    if (checkFourBallOrOut()) {
      return;
    }

    final Batting generateValue = randomValueGenerator.generator();
    final BullseyeState bullseyeState = bullseyeManager.confirmBullseye(generateValue);
    if (bullseyeState == BullseyeState.NOT_BULLSEYE) {
      plateAppearances.batting(generateValue);
      return;
    }
    doBullseye(bullseyeState);
  }

  public BattingResult getBattingResult() {
    Integer strikeCount = plateAppearances.strikeCount();
    if (strikeCount.equals(BattingResult.OUT.getValue())) {
      return BattingResult.OUT;
    }

    Integer ballCount = plateAppearances.ballCount();
    if (ballCount.equals(BattingResult.FOUR_BALL.getValue())) {
      return BattingResult.FOUR_BALL;
    }

    if (plateAppearances.isNotPlaying()) {
      return BattingResult.PLAYING;
    }

    return plateAppearances.getLastBattingResult();
  }

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

  public void doBullseye(final BullseyeState bullseyeState) {
    if (bullseyeState == BullseyeState.BULLSEYE_STRIKE) {
      plateAppearances.makeOut();
      return;
    }
    if (bullseyeState == BullseyeState.BULLSEYE_BALL) {
      plateAppearances.batting(Batting.HIT);
      return;
    }
    if (bullseyeState == BullseyeState.HOME_RUN) {
      score.addScore(1);
    }
  }

  public int getScore() {
    return score.getScore();
  }

  public void initScore() {score.init();}
}
