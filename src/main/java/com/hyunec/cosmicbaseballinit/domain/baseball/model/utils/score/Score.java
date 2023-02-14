package com.hyunec.cosmicbaseballinit.domain.baseball.model.utils.score;

import lombok.Getter;

@Getter
public class Score {
  private int score;

  public void addScore(int score) {
    this.score += score;
  }

  public void init() {
    score = 0;
  }
}
