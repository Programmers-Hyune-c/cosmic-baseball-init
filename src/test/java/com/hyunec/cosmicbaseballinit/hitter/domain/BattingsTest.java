package com.hyunec.cosmicbaseballinit.hitter.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static com.hyunec.cosmicbaseballinit.hitter.domain.BattingsResult.FOUR_BALL;
import static com.hyunec.cosmicbaseballinit.hitter.domain.BattingsResult.OUT;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class BattingsTest {

    @DisplayName("스트라이트 세번이면 아웃을 반환한다.")
    @Test
    void outResultTest() {
        Battings battings = new Battings(List.of(
                Batting.STRIKE, Batting.STRIKE, Batting.STRIKE
        ));

        String actual = battings.battingResult();

        assertThat(actual).isEqualTo(OUT.name());
    }

    @DisplayName("볼 네번이면 포볼을 반환한다.")
    @Test
    void fourBallTest() {
        Battings battings = new Battings(List.of(
                Batting.BALL, Batting.BALL, Batting.BALL, Batting.BALL
        ));

        String actual = battings.battingResult();

        assertThat(actual).isEqualTo(FOUR_BALL.name());
    }

    @DisplayName("그대로 배팅 결과를 반환한다.")
    @ParameterizedTest
    @MethodSource("parameters")
    void battingResult(Batting batting) {
        Battings battings = new Battings(List.of(batting));

        String actual = battings.battingResult();

        assertThat(actual).isEqualTo(batting.name());
    }

    private static Stream<Arguments> parameters() {
        return Stream.of(
                arguments(Batting.STRIKE),
                arguments(Batting.BALL),
                arguments(Batting.HIT)
        );
    }

    @Test
    void isNotFinishBaseBall() {
        Battings battings = new Battings(List.of(Batting.STRIKE,
                Batting.STRIKE, Batting.STRIKE));

        String battingResult = battings.battingResult();
        assertThat(battings.isFinishPlaceApperances(battingResult)).isTrue();
    }

}
