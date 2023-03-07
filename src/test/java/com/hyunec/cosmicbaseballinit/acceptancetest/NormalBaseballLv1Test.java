package com.hyunec.cosmicbaseballinit.acceptancetest;

import static org.assertj.core.api.Assertions.assertThat;

import com.hyunec.cosmicbaseballinit.batter.domain.Batting;
import com.hyunec.cosmicbaseballinit.batter.domain.BattingResult;
import com.hyunec.cosmicbaseballinit.batter.service.BattingStrategy;
import com.hyunec.cosmicbaseballinit.batter.service.RandomBattingStrategy;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class NormalBaseballLv1Test {

    static List<BattingResult> results = new ArrayList<>();

    @BeforeAll
    static void setUp() {
        // given
        BattingStrategy strategy = new RandomBattingStrategy();
        IntStream.range(0, 100).forEach(i -> {
            BattingResult result = Batting.of(strategy).getResult();
            results.add(result);
        });
    }


    @DisplayName("strike, ball, hit 는 같은 확률 입니다.")
    @Test
    void t1() {
        // then
        long strikeCount = results.stream().filter(result -> result == BattingResult.STRIKE).count();
        long ballCount = results.stream().filter(result -> result == BattingResult.BALL).count();
        long hitCount = results.stream().filter(result -> result == BattingResult.HIT).count();

        assertThat(strikeCount + ballCount + hitCount).isEqualTo(100);
    }

    @DisplayName("타격 결과는 strike, ball, hit 입니다.")
    @Test
    void t2() {
        // then
        assertThat(results).contains(BattingResult.STRIKE, BattingResult.BALL, BattingResult.HIT);
    }
}
