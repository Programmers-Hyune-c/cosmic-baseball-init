package com.hyunec.cosmicbaseballinit.acceptancetest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doReturn;

import com.hyunec.cosmicbaseballinit.domain.baseball.model.Batting;
import com.hyunec.cosmicbaseballinit.domain.baseball.model.BattingResult;
import com.hyunec.cosmicbaseballinit.domain.baseball.model.exception.ExceptionMessage;
import com.hyunec.cosmicbaseballinit.domain.baseball.model.service.BaseballManager;
import com.hyunec.cosmicbaseballinit.domain.baseball.model.service.bullseye.BullseyeManager;
import com.hyunec.cosmicbaseballinit.domain.baseball.model.service.bullseye.BullseyeState;
import com.hyunec.cosmicbaseballinit.domain.baseball.model.utils.generator.RandomValueGenerator;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class NormalBaseballLv1Test {

  @Autowired
  private BaseballManager baseballService;

  @SpyBean
  private BullseyeManager bullseyeManager;

  @SpyBean
  private RandomValueGenerator randomValueGenerator;

  @AfterEach
  void afterEach() {
    final BattingResult result = baseballService.getBattingResult();
    if (result == BattingResult.FOUR_BALL || result == BattingResult.OUT)
      baseballService.newGame();
  }

  @DisplayName("strike, ball, hit 는 같은 확률 입니다.")
  @ParameterizedTest
  @ValueSource(ints = {0, 1, 2})
  void t1(int number) {
    //given
    int MAX_NUMBER = 3;
    given(randomValueGenerator.getRandomNumber(Batting.getBattingSize())).willReturn(number);

    //when
    Batting generateValue = randomValueGenerator.generator();

    //then
    assertThat(generateValue).isEqualTo(Batting.of(number % MAX_NUMBER));
  }

  @DisplayName("3B 타석에서 타격 결과가 ball 이면 타석 결과는 four_ball 됩니다.")
  @Test
  void t2() {
    //given
    given(randomValueGenerator.generator()).willReturn(Batting.BALL);
    doReturn(BullseyeState.NOT_BULLSEYE).when(bullseyeManager).confirmBullseye(any());
    baseballService.batting();
    baseballService.batting();
    baseballService.batting();

    //when
    baseballService.batting();

    //then
    assertThat(baseballService.getBattingResult()).isEqualTo(BattingResult.FOUR_BALL);
  }

  @DisplayName("2S 타석에서 타격 결과가 strike 이면 타석 결과는 out 됩니다.")
  @Test
  void t3() {
    //given
    given(randomValueGenerator.generator()).willReturn(Batting.STRIKE);
    doReturn(BullseyeState.NOT_BULLSEYE).when(bullseyeManager).confirmBullseye(any());
    baseballService.batting();
    baseballService.batting();

    //when
    baseballService.batting();

    //then
    assertThat(baseballService.getBattingResult()).isEqualTo(BattingResult.OUT);
  }

  @DisplayName("진행 중인 타석이 있는 상태에서 새로운 타석을 진행할 수 없습니다.")
  @Test
  void t4() {
    //given
    given(randomValueGenerator.generator()).willReturn(Batting.BALL);
    doReturn(BullseyeState.NOT_BULLSEYE).when(bullseyeManager).confirmBullseye(any());
    baseballService.batting();

    //when, then
    assertThatThrownBy(() -> baseballService.newGame())
        .isInstanceOf(IllegalStateException.class)
        .hasMessageContaining(ExceptionMessage.FAILURE_PLATE_IS_EXIST_DO_NOT_PLAY_NEW_GAME);
  }

  @DisplayName("타석이 종료되면 초기화하여 새로 진행할 수 있습니다.")
  @Test
  void t5() {
    //given
    given(randomValueGenerator.generator()).willReturn(Batting.STRIKE);
    doReturn(BullseyeState.NOT_BULLSEYE).when(bullseyeManager).confirmBullseye(any());
    baseballService.batting();
    baseballService.batting();
    baseballService.batting();

    //when
    baseballService.newGame();

    //then
    assertThat(baseballService.getBattingResult()).isEqualTo(BattingResult.PLAYING);
  }

  @Nested
  class PlateAppearancesTest {
    @DisplayName("타석 결과 - 아웃")
    @Test
    void atBatResultOut() {
      // given
      given(randomValueGenerator.generator()).willReturn(Batting.STRIKE);
      doReturn(BullseyeState.NOT_BULLSEYE).when(bullseyeManager).confirmBullseye(any());
      baseballService.batting();
      baseballService.batting();


      // when
      assertThat(baseballService.getBattingResult()).isEqualTo(BattingResult.STRIKE);
      baseballService.batting();

      // then
      assertThat(baseballService.getBattingResult()).isEqualTo(BattingResult.OUT);
    }

    @DisplayName("타석 결과 - 포볼")
    @Test
    void atBatResultsFourBall() {
      // given
      given(randomValueGenerator.generator()).willReturn(Batting.BALL);
      doReturn(BullseyeState.NOT_BULLSEYE).when(bullseyeManager).confirmBullseye(any());
      baseballService.batting();
      baseballService.batting();
      baseballService.batting();

      // when
      assertThat(baseballService.getBattingResult()).isEqualTo(BattingResult.BALL);
      baseballService.batting();

      // then
      assertThat(baseballService.getBattingResult()).isEqualTo(BattingResult.FOUR_BALL);
    }

    @DisplayName("타석 결과 - 안타")
    @Test
    void atBatResultHits() {
      // given
      given(randomValueGenerator.generator()).willReturn(Batting.HIT);
      doReturn(BullseyeState.NOT_BULLSEYE).when(bullseyeManager).confirmBullseye(any());

      // when
      baseballService.batting();

      // then
      assertThat(baseballService.getBattingResult()).isEqualTo(BattingResult.HIT);
    }
  }
}
