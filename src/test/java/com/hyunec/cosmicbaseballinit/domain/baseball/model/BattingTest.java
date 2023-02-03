package com.hyunec.cosmicbaseballinit.domain.baseball.model;

import static org.junit.jupiter.api.Assertions.*;

import com.hyunec.cosmicbaseballinit.domain.baseball.model.exception.ExceptionMessage;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class BattingTest {

  @DisplayName("Batting값을 변환하려는 수는 Batting의 size 보다 클 수 없고 0보다 커야합니다.")
  @Test
  void atBattingOfIsBiggerThanZeroOrSmallerThanBattingSize() {
    //given
    int number1 = Batting.getBattingSize() + 1;
    int number2 = -1;

    //when, then
    Assertions.assertThatThrownBy(() -> Batting.of(number1))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessageContaining(ExceptionMessage.VALUE_MUST_BE_SMALLER_THAN_BATTING_SIZE);

    Assertions.assertThatThrownBy(() -> Batting.of(number2))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessageContaining(ExceptionMessage.VALUE_MUST_BE_SMALLER_THAN_BATTING_SIZE);
  }

}