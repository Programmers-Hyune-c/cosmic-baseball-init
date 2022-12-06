package com.hyunec.cosmicbaseballinit.hitter.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class BattingResultTest {

    @DisplayName("배팅 결과를 반환한다.")
    @ParameterizedTest
    @MethodSource("parameters")
    void from(Batting batting, BattingResult expected) {
        BattingResult actual = BattingResult.from(batting);

        assertThat(actual).isEqualTo(expected);
    }

    private static Stream<Arguments> parameters() {
        return Stream.of(
                arguments(Batting.STRIKE, BattingResult.STRIKE),
                arguments(Batting.BALL, BattingResult.BALL),
                arguments(Batting.HIT, BattingResult.HIT)
        );
    }
}
