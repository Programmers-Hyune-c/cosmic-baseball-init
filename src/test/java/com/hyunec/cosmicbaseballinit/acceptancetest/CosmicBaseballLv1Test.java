package com.hyunec.cosmicbaseballinit.acceptancetest;

import static org.assertj.core.api.Assertions.assertThat;

import com.hyunec.cosmicbaseballinit.batter.domain.BattingResult;
import com.hyunec.cosmicbaseballinit.batter.service.BattingFactory;
import com.hyunec.cosmicbaseballinit.batter.service.RandomBattingFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CosmicBaseballLv1Test {

    static List<BattingResult> results = new ArrayList<>();

    @BeforeAll
    static void setUp() {
        // given
        BattingFactory factory = new RandomBattingFactory();
        IntStream.range(0, 100).forEach(i -> {
            BattingResult result = factory.generate();
            results.add(result);
        });
    }

    @DisplayName("타격 결과는 모두 같은 확률을 가집니다.")
    @Test
    void t1() {
        // then
        long strikeCount = results.stream().filter(result -> result == BattingResult.STRIKE).count();
        long ballCount = results.stream().filter(result -> result == BattingResult.BALL).count();
        long hitCount = results.stream().filter(result -> result == BattingResult.HIT).count();
        long doubleBallCount = results.stream().filter(result -> result == BattingResult.DOUBLE_BALL).count();
        long doubleStrikeCount = results.stream().filter(result -> result == BattingResult.DOUBLE_STRIKE).count();

        assertThat(strikeCount + ballCount + hitCount + doubleBallCount + doubleStrikeCount).isEqualTo(100);
    }

    @DisplayName("타격 결과는 strike, ball, hit, double_ball, double_strike 입니다.")
    @Test
    void t2() {
        assertThat(results).contains(
            BattingResult.STRIKE,
            BattingResult.BALL,
            BattingResult.HIT,
            BattingResult.DOUBLE_BALL,
            BattingResult.DOUBLE_STRIKE
        );
    }
}
