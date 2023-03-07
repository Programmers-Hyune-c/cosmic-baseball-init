package com.hyunec.cosmicbaseballinit.acceptancetest;

import static org.assertj.core.api.Assertions.assertThat;

import com.hyunec.cosmicbaseballinit.batter.domain.BattingResult;
import com.hyunec.cosmicbaseballinit.batter.service.BattingFactory;
import com.hyunec.cosmicbaseballinit.batter.service.RandomBattingFactory;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class NormalBaseballLv1Test {

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


    @DisplayName("strike, ball, hit 는 같은 확률 입니다.")
    @Test
    void t1() {
        // when
        Map<BattingResult, Integer> counts = new HashMap<>();
        for (BattingResult result : results) {
            counts.put(result, counts.getOrDefault(result, 0) + 1);
        }

        // then
        assertThat(counts.values().stream().mapToInt(Integer::intValue).sum()).isEqualTo(100);
    }

    @DisplayName("타격 결과는 strike, ball, hit 입니다.")
    @Test
    void t2() {
        // then
        assertThat(results).contains(BattingResult.STRIKE, BattingResult.BALL, BattingResult.HIT);
    }
}
