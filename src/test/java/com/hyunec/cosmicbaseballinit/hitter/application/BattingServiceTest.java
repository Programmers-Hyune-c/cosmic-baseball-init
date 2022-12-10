package com.hyunec.cosmicbaseballinit.hitter.application;

import com.hyunec.cosmicbaseballinit.hitter.domain.Batting;
import com.hyunec.cosmicbaseballinit.hitter.domain.BattingRepository;
import com.hyunec.cosmicbaseballinit.hitter.domain.Battings;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

@SpringBootTest
class BattingServiceTest {

    @Autowired
    BattingService battingService;

    @Autowired
    BattingRepository battingRepository;

    @AfterEach
    void setUp() {
        battingRepository.clear();
    }

    @DisplayName("저장된 배팅 리스트들을 반환한다.")
    @ParameterizedTest
    @MethodSource("parameters")
    void swing(Batting batting) {
        Battings actual = battingService.swing(batting);

        assertThat(actual.getBattings()).containsExactly(batting);
    }

    private static Stream<Arguments> parameters() {
        return Stream.of(
                arguments(Batting.STRIKE),
                arguments(Batting.BALL),
                arguments(Batting.HIT)
        );
    }

    @DisplayName("스트라이크, 볼이면 클리어 하지 않는다.")
    @ParameterizedTest
    @ValueSource(strings = {
            "STRIKE",
            "BALL"
    })
    void notClearBatting(String battingResult) {
        battingRepository.save(Batting.STRIKE);

        battingService.clearBattings(battingResult);

        assertThat(battingRepository.findAll()).hasSize(1);
    }

    @DisplayName("아웃, 볼넷, 히트 이면 클리어 한다.")
    @ParameterizedTest
    @ValueSource(strings = {
            "HIT",
            "OUT",
            "FOUR_BALL"
    })
    void clearBatting(String battingResult) {
        battingRepository.save(Batting.STRIKE);

        battingService.clearBattings(battingResult);

        assertThat(battingRepository.findAll()).isEmpty();
    }

}
