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
    void generateTest(Batting result) {
        randomGenerate = new RandomGenerate() {
            @Override
            public Batting generate() {
                return result;
            }
        };

        Batting actual = randomGenerate.generate();

        assertThat(actual).isEqualTo(result);
    }

    private static Stream<Arguments> parameters() {
        return Stream.of(
                arguments(Batting.STRIKE),
                arguments(Batting.BALL),
                arguments(Batting.HIT)
        );
    }
}
