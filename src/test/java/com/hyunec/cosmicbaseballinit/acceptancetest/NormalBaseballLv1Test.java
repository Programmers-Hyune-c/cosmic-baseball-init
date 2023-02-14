package com.hyunec.cosmicbaseballinit.acceptancetest;

import static org.assertj.core.api.Assertions.assertThat;

import com.hyunec.cosmicbaseballinit.domain.baseball.model.Batting;
import com.hyunec.cosmicbaseballinit.domain.baseball.model.BattingResult;
import com.hyunec.cosmicbaseballinit.domain.baseball.model.GameEntity;
import com.hyunec.cosmicbaseballinit.domain.baseball.repository.PlateAppearances;
import com.hyunec.cosmicbaseballinit.domain.baseball.service.GameService;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class NormalBaseballLv1Test {

    @Autowired
    GameService gameService;

    @Autowired
    PlateAppearances plateAppearances;

    @BeforeEach
    void setup() {
        plateAppearances.clear();
    }

    @DisplayName("strike, ball, hit 는 같은 확률 입니다.")
    @RepeatedTest(value = 100)
//    @Test
    void t1() {
        // Given
        // 확률이 1/n 이고 3만번 시행했을 때 평균인 tc/n 으로부터 신뢰수준 99%을 만족함
        int tc = 3 * 10000;
        double deviation = 0.05;

        // When
        Map<Batting, Integer> counter = new HashMap<>();
        for (int i = 0; i < tc; i++) {
            Batting result = Batting.generate();
            counter.put(result, counter.getOrDefault(result, 0) + 1);
        }

        int lowerBound = (int) Math.floor((double) (tc / 3) * (1.0 - deviation));
        int upperBound = (int) Math.floor((double) (tc / 3) * (1.0 + deviation));
        for (Integer val : counter.values()) {
            assertThat(val).isBetween(lowerBound, upperBound);
        }
    }

    @DisplayName("3B 타석에서 타격 결과가 ball 이면 타석 결과는 four_ball 됩니다.")
    @Test
    void t2() {
        // Given
        plateAppearances.batting(Batting.BALL);
        plateAppearances.batting(Batting.BALL);
        plateAppearances.batting(Batting.BALL);

        // When
        plateAppearances.batting(Batting.BALL);

        // Then
        assertThat(plateAppearances.result()).isNotEqualTo(BattingResult.OUT);
        assertThat(plateAppearances.result()).isEqualTo(BattingResult.FOUR_BALL);
    }

    @DisplayName("2S 타석에서 타격 결과가 strike 이면 타석 결과는 out 됩니다.")
    @Test
    void t3() {
        // Given
        plateAppearances.batting(Batting.STRIKE);
        plateAppearances.batting(Batting.STRIKE);

        // When
        plateAppearances.batting(Batting.STRIKE);

        // Then
        assertThat(plateAppearances.result()).isEqualTo(BattingResult.OUT);
    }

    @DisplayName("진행 중인 타석이 있는 상태에서 새로운 타석을 진행할 수 없습니다.")
    @Test
    void t4() {
        plateAppearances.batting(Batting.STRIKE);
        assertThat(gameService.initGame())
            .isEqualTo(
                GameEntity.builder().message("아직 게임이 끝나지 않았습니다.").build()
            );


    }

    @DisplayName("타석이 종료되면 초기화하여 새로 진행할 수 있습니다.")
    @Test
    void t5() {
        // Given
        plateAppearances.batting(Batting.STRIKE);
        plateAppearances.batting(Batting.STRIKE);
        plateAppearances.batting(Batting.STRIKE);
        // When
        // Then

        assertThat(gameService.initGame()).isEqualTo(
            GameEntity.builder().message("새로운 게임이 생성되었습니다.").build()
        );

        plateAppearances.batting(Batting.HIT);
        assertThat(gameService.initGame()).isEqualTo(
            GameEntity.builder().message("새로운 게임이 생성되었습니다.").build()
        );

        plateAppearances.batting(Batting.BALL);
        plateAppearances.batting(Batting.BALL);
        plateAppearances.batting(Batting.BALL);
        plateAppearances.batting(Batting.BALL);
        assertThat(gameService.initGame()).isEqualTo(
            GameEntity.builder().message("새로운 게임이 생성되었습니다.").build()
        );
    }
}
