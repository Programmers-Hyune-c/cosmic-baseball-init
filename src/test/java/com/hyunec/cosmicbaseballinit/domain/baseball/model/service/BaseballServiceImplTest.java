package com.hyunec.cosmicbaseballinit.domain.baseball.model.service;

import static org.assertj.core.api.Assertions.assertThat;

import com.hyunec.cosmicbaseballinit.domain.baseball.model.BattingResult;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest

class BaseballServiceImplTest {
  @Autowired
  private BaseballManager baseballService;

  @DisplayName("배팅을 시작하지 않았을 경우 result 반환시 NOTHING이 나와야합니다.")
  @Test
  void atBattingIsNotStartingThenReturnNOTHING() {
    assertThat(baseballService.getBattingResult()).isEqualTo(BattingResult.PLAYING);
  }
}