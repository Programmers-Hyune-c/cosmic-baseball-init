package com.hyunec.cosmicbaseballinit.acceptancetest;

import static com.hyunec.cosmicbaseballinit.service.BattingService.RANDOM;
import static org.assertj.core.api.Assertions.assertThat;

import com.hyunec.cosmicbaseballinit.domain.Ball;
import com.hyunec.cosmicbaseballinit.domain.BattingResult;
import com.hyunec.cosmicbaseballinit.domain.DoubleStrike;
import com.hyunec.cosmicbaseballinit.domain.Hit;
import com.hyunec.cosmicbaseballinit.domain.Strike;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.assertj.core.api.Assertions;
import org.assertj.core.data.Percentage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;


class CosmicBaseballLv1Test {

    private final List<BattingResult> battingResults =
        new ArrayList<>(List.of(new Strike(), new Hit(), new Ball(), new DoubleStrike()));

    private final BattingResult strike = battingResults.get(0);
    private final BattingResult hit = battingResults.get(1);
    private final BattingResult ball = battingResults.get(2);
    private final BattingResult doubleStrike = battingResults.get(3);

    private final Map<String, Integer> store =
        new HashMap<>(Map.of("strike", 0, "ball", 0));


    @BeforeEach
    void init() {
        store.keySet().forEach(k -> store.put(k, 0));
    }

    @DisplayName("타격 결과는 모두 같은 확률을 가집니다.")
    @RepeatedTest(10)
    void t1() {
        assertThat(getTotalRandomPick()).isCloseTo(25_000, Percentage.withPercentage(1));
    }

    @DisplayName("strike 시 strike 카운트가 1 증가합니다.")
    @Test
    void strikeTest() {
        strike.call(store);
        Assertions.assertThat(store).containsEntry("strike", 1).containsEntry("ball", 0);
    }

    @DisplayName("ball 시 ball 카운트가 1 증가합니다.")
    @Test
    void ballTest() {
        ball.call(store);
        Assertions.assertThat(store).containsEntry("strike", 0).containsEntry("ball", 1);
    }

    @DisplayName("double strike 시 strike 카운트가 2 증가합니다.")
    @Test
    void doubleStrikeTest() {
        doubleStrike.call(store);
        Assertions.assertThat(store).containsEntry("strike", 2).containsEntry("ball", 0);
    }

    @DisplayName("hit 시 결과 카운트가 리셋됩니다.")
    @Test
    void hitTest() {
        store.keySet().forEach(k -> store.put(k, 2));
        hit.call(store);
        Assertions.assertThat(store).containsEntry("strike", 0).containsEntry("ball", 0);
    }

    @DisplayName("타격 결과는 strike, ball, hit, double_ball, double_strike 입니다.")
    @RepeatedTest(10)
    void t2() {
    }

    private int getTotalRandomPick() {
        int count = 0;
        for (int i = 0; i < 100000; i++) {
            BattingResult result = battingResults.get(RANDOM.nextInt(battingResults.size()));
            if (result == battingResults.get(0)) {
                count++;
            }
        }
        return count;
    }
}
