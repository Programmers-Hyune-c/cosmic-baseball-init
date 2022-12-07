package com.hyunec.cosmicbaseballinit.hitter.domain;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class BattingTest {

    RandomGenerate randomGenerate;

    @ParameterizedTest
    @MethodSource("parameters")
    void generateTest(int ordinal, Batting result) {
        randomGenerate = new RandomGenerate() {
            @Override
            public int generate() {
                return ordinal;
            }
        };

        Batting actual = Batting.generate(randomGenerate);

        assertThat(actual).isEqualTo(result);
    }

    private static Stream<Arguments> parameters() {
        return Stream.of(
                arguments(0, Batting.STRIKE),
                arguments(1, Batting.BALL),
                arguments(2, Batting.HIT)
        );
    }
}
