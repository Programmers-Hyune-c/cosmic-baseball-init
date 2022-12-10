package com.hyunec.cosmicbaseballinit.hitter.ui.component;

import com.hyunec.cosmicbaseballinit.hitter.domain.Batting;
import com.hyunec.cosmicbaseballinit.hitter.domain.Battings;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.stream.Stream;

import static com.hyunec.cosmicbaseballinit.hitter.domain.Batting.BALL;
import static com.hyunec.cosmicbaseballinit.hitter.domain.Batting.STRIKE;
import static com.hyunec.cosmicbaseballinit.hitter.domain.BattingsResult.FOUR_BALL;
import static com.hyunec.cosmicbaseballinit.hitter.domain.BattingsResult.OUT;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

@SpringBootTest(classes = PlateAppearance.class)
class PlateAppearanceTest {

    @Autowired
    PlateAppearance plateAppearance;

    @DisplayName("경기 결과를 반환한다.")
    @ParameterizedTest
    @MethodSource("parameters")
    void result(List<Batting> battingList, String expected) {
        Battings battings = new Battings(battingList);

        String actual = plateAppearance.result(battings);

        assertThat(actual).isEqualTo(expected);
    }

    private static Stream<Arguments> parameters() {
        return Stream.of(
                arguments(List.of(STRIKE, STRIKE, STRIKE), OUT.name()),
                arguments(List.of(BALL, BALL, BALL, BALL), FOUR_BALL.name()),
                arguments(List.of(BALL, BALL, BALL), BALL.name())
        );
    }
}
