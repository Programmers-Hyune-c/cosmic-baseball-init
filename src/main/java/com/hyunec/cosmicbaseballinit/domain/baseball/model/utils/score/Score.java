package com.hyunec.cosmicbaseballinit.domain.baseball.model.utils.score;

public class Score {
  private int score;

  public int getScore() {
    return score;
  }

  public void addScore(int score) {
    this.score += score;
  }

  public void init() {
    score = 0;
  }
}
