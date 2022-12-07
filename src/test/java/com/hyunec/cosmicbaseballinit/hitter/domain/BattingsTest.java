package com.hyunec.cosmicbaseballinit.hitter.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class BattingsTest {

    RandomGenerate randomGenerate;

    @BeforeEach
    void setUp() {
        randomGenerate = new RandomGenerate() {
            @Override
            public int generate() {
                return 0;
            }
        };
    }


    @DisplayName("스트라이트 세번이면 아웃을 반환한다.")
    @Test
    void outResultTest() {
        Battings battings = new Battings(List.of(
                Batting.STRIKE, Batting.STRIKE, Batting.STRIKE
        ), new BattingRandomGenerate());

        BattingResult actual = battings.battingResult(Batting.STRIKE);

        assertThat(actual).isEqualTo(BattingResult.OUT);
    }

    @DisplayName("볼 네번이면 포볼을 반환한다.")
    @Test
    void fourBallTest() {
        Battings battings = new Battings(List.of(
                Batting.BALL, Batting.BALL, Batting.BALL, Batting.BALL
        ), new BattingRandomGenerate());

        BattingResult actual = battings.battingResult(Batting.BALL);

        assertThat(actual).isEqualTo(BattingResult.FOUR_BALL);
    }

    @DisplayName("그대로 배팅 결과를 반환한다.")
    @ParameterizedTest
    @MethodSource("parameters")
    void battingResult(Batting batting, BattingResult result) {
        Battings battings = new Battings(Collections.emptyList(), randomGenerate);

        BattingResult actual = battings.battingResult(batting);

        assertThat(actual).isEqualTo(result);
    }

    private static Stream<Arguments> parameters() {
        return Stream.of(
                arguments(Batting.STRIKE, BattingResult.STRIKE),
                arguments(Batting.BALL, BattingResult.BALL),
                arguments(Batting.HIT, BattingResult.HIT)
        );
    }

}
