package com.hyunec.cosmicbaseballinit.acceptancetest;


import static com.hyunec.cosmicbaseballinit.service.BallCountService.COUNT_STORE;
import static com.hyunec.cosmicbaseballinit.service.BattingResult.BALL;
import static com.hyunec.cosmicbaseballinit.service.BattingResult.DOUBLE_STRIKE;
import static com.hyunec.cosmicbaseballinit.service.BattingResult.HIT;
import static com.hyunec.cosmicbaseballinit.service.BattingResult.STRIKE;
import static org.assertj.core.api.Assertions.assertThat;

import com.hyunec.cosmicbaseballinit.service.BattingResult;
import com.hyunec.cosmicbaseballinit.util.RandomFactory;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import org.assertj.core.api.SoftAssertions;
import org.assertj.core.data.Percentage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;


class CosmicBaseballLv1Test extends ResultCount {

    private Map<String, Integer> mockStore;

    @BeforeEach
    void init() {
        mockStore = new HashMap<>(Map.of("strike", 0, "ball", 0));
    }

    @DisplayName("타격 결과는 모두 같은 확률을 가집니다.")
    @RepeatedTest(10)
    void t1() {
        Random random = RandomFactory.getInstance();
        BattingResult battingResult = getResult(random);
        int count = 0;
        int enumCount = 4;
        count = super.getBattingResultCount(battingResult.ordinal(), count, random, enumCount);

        assertThat(count).isCloseTo(25_000, Percentage.withPercentage(1));
    }

    @DisplayName("strike가 나올 시 스트라이크 카운트를 한 개 증가시킵니다.")
    @Test
    void strikeCountTest1() {
        addCount(STRIKE);
        assertThat(mockStore).containsEntry("strike",1);
    }

    @DisplayName("ball이 나올 시 스트라이크 카운트를 한 개 증가시킵니다.")
    @Test
    void ballCountTest2() {
        addCount(BALL);
        assertThat(mockStore).containsEntry("ball",1);
    }

    @DisplayName("hit가 나올 시 볼 카운트를 리셋 시킵니다.")
    @Test
    void hitCount() {
        addCount(HIT);

        SoftAssertions.assertSoftly(soft -> {
            soft.assertThat(mockStore).containsEntry("ball",0);
            soft.assertThat(mockStore).containsEntry("strike",0);
        });
    }

    @DisplayName("double_strike가 나올 시 스트라이크 카운트를 두 개 증가시킵니다.")
    @Test
    void strikeCountTest2() {
        addCount(DOUBLE_STRIKE);
        assertThat(mockStore.get("strike")).isEqualTo(2);
    }

    @DisplayName("타격 결과는 strike, ball, hit, double_ball, double_strike 입니다.")
    void t2() {

    }


    public void addCount(BattingResult result) {

        switch (result.name()) {
            case "STRIKE":
                mockStore.compute("strike", (k, v) -> (v + 1));
                break;
            case "BALL":
                mockStore.compute("ball", (k, v) -> (v + 1));
                break;
            case "DOUBLE_STRIKE":
                mockStore.compute("strike", (k, v) -> (v + 2));
            default:
                resetStore();
        }
    }


    private BattingResult getResult(Random random) {
        switch (random.nextInt(4)) {
            case 0:
                return STRIKE;
            case 1:
                return HIT;
            case 2:
                return BALL;
            default:
                return DOUBLE_STRIKE;
        }
    }

    private void resetStore() {
        COUNT_STORE.keySet().forEach(key -> COUNT_STORE.put(key, 0));
    }
}
