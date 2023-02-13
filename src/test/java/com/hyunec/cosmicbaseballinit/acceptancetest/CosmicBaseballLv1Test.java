package com.hyunec.cosmicbaseballinit.acceptancetest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.BDDMockito.given;

import com.hyunec.cosmicbaseballinit.domain.baseball.model.Batting;
import com.hyunec.cosmicbaseballinit.domain.baseball.model.BattingResult;
import com.hyunec.cosmicbaseballinit.domain.baseball.model.service.BaseballServiceImpl;
import com.hyunec.cosmicbaseballinit.domain.baseball.model.utils.generator.RandomBattingGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CosmicBaseballLv1Test {

    @InjectMocks
    private BaseballServiceImpl baseballService;

    @Spy
    private RandomBattingGenerator battingGenerator;

    @DisplayName("strike 의 20% 는 bullseye_strike 입니다.")
    @ParameterizedTest
    @ValueSource(ints = {0, 1})
    void t1(int number) {
        //given
        given(battingGenerator.getRandomNumber(10)).willReturn(number);
        given(battingGenerator.getRandomNumber(Batting.getBattingSize())).willReturn(Batting.STRIKE.getValue());

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
        given(battingGenerator.getRandomNumber(Batting.getBattingSize())).willReturn(Batting.BALL.getValue());

        //when
        baseballService.batting();

        //then
        assertThat(baseballService.getBattingResult()).isEqualTo(BattingResult.HIT);
    }

    @DisplayName("hit 의 20% 는 homerun 입니다.")
    @Test
    void t3() {
        throw new RuntimeException("Not yet implemented");
    }

    @DisplayName("타격 결과가 bullseye_strike 이면 타석 결과는 out 됩니다.")
    @Test
    void t4() {
        //given
        given(battingGenerator.getRandomNumber(10)).willReturn(1);
        given(battingGenerator.getRandomNumber(Batting.getBattingSize())).willReturn(Batting.STRIKE.getValue());

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
        given(battingGenerator.getRandomNumber(Batting.getBattingSize())).willReturn(Batting.BALL.getValue());

        //when
        baseballService.batting();

        //then
        assertThat(baseballService.getBattingResult()).isEqualTo(BattingResult.HIT);
    }
}
