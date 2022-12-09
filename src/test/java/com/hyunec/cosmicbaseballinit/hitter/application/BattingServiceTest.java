package com.hyunec.cosmicbaseballinit.hitter.application;

import com.hyunec.cosmicbaseballinit.hitter.domain.Batting;
import com.hyunec.cosmicbaseballinit.hitter.domain.BattingRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
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

    Batting[] battings;

    @DisplayName("아웃, 폴넷, 히트면 데이터가 리셋된다.")
    @ParameterizedTest
    @MethodSource("parameters")
    void strikeTest(Batting batting, int count) {
        battings = new Batting[count - 1];
        for (int i = 1; i < count; i++) {
            battingService.swing(batting);
            battings[i - 1] = batting;
        }
        assertThat(battingRepository.findAll()).containsExactly(battings);

        battingService.swing(batting);
        assertThat(battingRepository.findAll()).hasSize(0);
    }

    private static Stream<Arguments> parameters() {
        return Stream.of(
                arguments(Batting.STRIKE, 3),
                arguments(Batting.BALL, 4),
                arguments(Batting.HIT, 1)
        );
    }
}
