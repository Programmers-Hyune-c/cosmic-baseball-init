package com.hyunec.cosmicbaseballinit.domain.baseball.model.service;

import com.hyunec.cosmicbaseballinit.domain.baseball.model.Batting;
import com.hyunec.cosmicbaseballinit.domain.baseball.model.BattingResult;
import com.hyunec.cosmicbaseballinit.domain.baseball.model.PlateAppearances;
import com.hyunec.cosmicbaseballinit.domain.baseball.model.dto.NewGameResponse;
import com.hyunec.cosmicbaseballinit.domain.baseball.model.exception.ExceptionMessage;
import com.hyunec.cosmicbaseballinit.domain.baseball.model.utils.generator.BattingGenerator;
import com.hyunec.cosmicbaseballinit.domain.baseball.model.utils.generator.RandomBattingQualifier;
import com.hyunec.cosmicbaseballinit.domain.baseball.model.utils.score.Score;
import java.time.LocalDateTime;
import org.springframework.stereotype.Service;

@Service
public class BaseballServiceImpl implements BaseballService {

  private static final String SUCCESS_NEW_GAME = "다음 게임을 시작합니다";
  private static final boolean END_GAME = true;
  private static final int MAX_PROBABILITY_SIZE = 10;
  private static final int BULLSEYE_PROBABILITY = 2;
  private static final boolean BULLEYES = true;

  private final PlateAppearances plateAppearances = new PlateAppearances();
  private final BattingGenerator battingGenerator;
  private final Score score = new Score();

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
    if (confirmBullEyes(generateValue))
      return;
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

    if (plateAppearances.isNotPlaying()) {
      return BattingResult.PLAYING;
    }

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

  private boolean confirmBullEyes(Batting generatedBatting) {
    if (generateRandomNumber(MAX_PROBABILITY_SIZE)) {
      if (generatedBatting == Batting.STRIKE) {
        makeBullEyesStrike();
      }
      else if (generatedBatting == Batting.BALL) {
        makeBullEyesBall();
      }
      else if (generatedBatting == Batting.HIT) {
        makeHomeRun();
      }
      return BULLEYES;
    }
    return !BULLEYES;
  }

  public boolean generateRandomNumber(int range) {
    final int randomNumber = battingGenerator.getRandomNumber(range);
    if (randomNumber < BULLSEYE_PROBABILITY) {
      return BULLEYES;
    }
    return !BULLEYES;
  }

  private void makeBullEyesStrike() {
    final Integer strikeCount = plateAppearances.strikeCount();
    for (int i = strikeCount; i < BattingResult.OUT.getValue(); i++) {
      plateAppearances.batting(Batting.STRIKE);
    }
  }

  private void makeHomeRun() {
    score.addScore(1);
  }

  private void makeBullEyesBall() {
    plateAppearances.batting(Batting.HIT);
  }

  public int getScore() {
    return score.getScore();
  }
}
