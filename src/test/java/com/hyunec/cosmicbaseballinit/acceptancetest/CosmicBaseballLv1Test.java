package com.hyunec.cosmicbaseballinit.acceptancetest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doReturn;

import com.hyunec.cosmicbaseballinit.domain.baseball.model.Batting;
import com.hyunec.cosmicbaseballinit.domain.baseball.model.BattingResult;
import com.hyunec.cosmicbaseballinit.domain.baseball.model.service.BaseballManager;
import com.hyunec.cosmicbaseballinit.domain.baseball.model.service.bullseye.BullseyeManager;
import com.hyunec.cosmicbaseballinit.domain.baseball.model.service.bullseye.BullseyeState;
import com.hyunec.cosmicbaseballinit.domain.baseball.model.utils.generator.RandomValueGenerator;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class CosmicBaseballLv1Test {

    @Autowired
    private BaseballManager baseballService;

    @SpyBean
    private RandomValueGenerator battingGenerator;

    @SpyBean
    private BullseyeManager bullseyeManager;

    @AfterEach
    void afterEach() {
        final BattingResult result = baseballService.getBattingResult();
        if (result == BattingResult.FOUR_BALL || result == BattingResult.OUT)
            baseballService.newGame();
    }

    @DisplayName("strike 의 20% 는 bullseye_strike 입니다.")
    @ParameterizedTest
    @ValueSource(ints = {0, 1})
    void t1(int number) {
        //given
        given(battingGenerator.getRandomNumber(10)).willReturn(number);
        doReturn(BullseyeState.BULLSEYE_STRIKE).when(bullseyeManager).confirmBullseye(any());

        //when
        baseballService.batting();

        //then
        assertThat(baseballService.getBattingResult()).isEqualTo(BattingResult.OUT);
        baseballService.newGame();
    }

    @DisplayName("ball 의 20% 는 bullseye_ball 입니다.")
    @ParameterizedTest
    @ValueSource(ints = {0, 1})
    void t2(int number) {
        //given
        given(battingGenerator.getRandomNumber(10)).willReturn(number);
        doReturn(BullseyeState.BULLSEYE_BALL).when(bullseyeManager).confirmBullseye(any());

        //when
        baseballService.batting();

        //then
        assertThat(baseballService.getBattingResult()).isEqualTo(BattingResult.HIT);
        baseballService.initScore();
    }

    @DisplayName("hit 의 20% 는 homerun 입니다.")
    @ParameterizedTest
    @ValueSource(ints = {0, 1})
    void t3(int number) {
        //given
        given(battingGenerator.getRandomNumber(10)).willReturn(number);
        given(battingGenerator.getRandomNumber(Batting.getBattingSize())).willReturn(Batting.HIT.getValue());

        //when
        baseballService.batting();

        //then
        assertThat(baseballService.getScore()).isEqualTo(1);
        baseballService.initScore();
    }

    @DisplayName("타격 결과가 bullseye_strike 이면 타석 결과는 out 됩니다.")
    @Test
    void t4() {
        //given
        given(battingGenerator.getRandomNumber(10)).willReturn(1);
        doReturn(BullseyeState.BULLSEYE_STRIKE).when(bullseyeManager).confirmBullseye(any());

        //when
        baseballService.batting();

        //then
        assertThat(baseballService.getBattingResult()).isEqualTo(BattingResult.OUT);
    }

    @DisplayName("타격 결과가 bullseye_ball 이면 타석 결과는 hit 됩니다.")
    @Test
    void t5() {
        //given
        given(battingGenerator.getRandomNumber(10)).willReturn(1);
        doReturn(BullseyeState.BULLSEYE_BALL).when(bullseyeManager).confirmBullseye(any());

        //when
        baseballService.batting();

        //then
        assertThat(baseballService.getBattingResult()).isEqualTo(BattingResult.HIT);
    }
}
