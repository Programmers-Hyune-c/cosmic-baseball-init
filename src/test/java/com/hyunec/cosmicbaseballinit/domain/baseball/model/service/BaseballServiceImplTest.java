package com.hyunec.cosmicbaseballinit.domain.baseball.model.service;

import static org.assertj.core.api.Assertions.assertThat;

import com.hyunec.cosmicbaseballinit.domain.baseball.model.BattingResult;
import com.hyunec.cosmicbaseballinit.domain.baseball.model.utils.generator.BattingGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class BaseballServiceImplTest {

  private BaseballService baseballService;

  @Autowired
  private BattingGenerator battingGenerator;

  @BeforeEach
  void setUp() {
    baseballService = new BaseballServiceImpl(battingGenerator);
  }

  @DisplayName("배팅을 시작하지 않았을 경우 result 반환시 NOTHING이 나와야합니다.")
  @Test
  void atBattingIsNotStartingThenReturnNOTHING() {
    assertThat(baseballService.getBattingResult()).isEqualTo(BattingResult.NOTHING);
  }
}