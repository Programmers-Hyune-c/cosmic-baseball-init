package com.hyunec.cosmicbaseballinit.domain.baseball.model;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.hyunec.cosmicbaseballinit.domain.baseball.model.exception.ExceptionMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BattingTest {

  @DisplayName("Batting값을 변환하려는 수는 Batting에 있는 값에 속해있어야 합니다.")
  @Test
  void atBattingOfIsBiggerThanZeroOrSmallerThanBattingSize() {
    //given
    int number1 = Batting.getBattingSize() + 1;
    int number2 = -1;

    //when, then
    assertThatThrownBy(() -> Batting.of(number1))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessageContaining(ExceptionMessage.NO_MATCH_BATTING_VALUE);

    assertThatThrownBy(() -> Batting.of(number2))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessageContaining(ExceptionMessage.NO_MATCH_BATTING_VALUE);
  }

}