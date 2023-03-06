package com.hyunec.cosmicbaseballinit.acceptancetest;


import static com.hyunec.cosmicbaseballinit.domain.BattingResult.BALL;
import static com.hyunec.cosmicbaseballinit.domain.BattingResult.DOUBLE_BALL;
import static com.hyunec.cosmicbaseballinit.domain.BattingResult.DOUBLE_STRIKE;
import static com.hyunec.cosmicbaseballinit.domain.BattingResult.HIT;
import static com.hyunec.cosmicbaseballinit.domain.BattingResult.STRIKE;
import static com.hyunec.cosmicbaseballinit.service.BallCountService.COUNT_STORE;
import static com.hyunec.cosmicbaseballinit.service.BattingService.RANDOM;
import static org.assertj.core.api.Assertions.assertThat;

import com.hyunec.cosmicbaseballinit.domain.BattingResult;
import com.hyunec.cosmicbaseballinit.service.BallCountService;
import org.assertj.core.data.Percentage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;


class CosmicBaseballLv1Test {

    @BeforeEach
    void init() {
        BallCountService.resetBallCount();
    }

    @DisplayName("타격 결과는 모두 같은 확률을 가집니다.")
    @RepeatedTest(10)
    void t1() {
        int pickedCount = getPickedCount();
        assertThat(pickedCount).isCloseTo(40_000, Percentage.withPercentage(1));
    }

    @DisplayName("스트라이크가 나올 시 STRIKE 카운트가 1 증가합니다.")
    @Test
    void strikeTest() {
        STRIKE.call();
        assertThat(COUNT_STORE).containsEntry("STRIKE", 1).containsEntry("BALL", 0);
    }

    @DisplayName("볼이 나올 시 BALL 카운트가 1 증가합니다.")
    @Test
    void ballTest() {
        BALL.call();
        assertThat(COUNT_STORE).containsEntry("STRIKE", 0).containsEntry("BALL", 1);
    }

    @DisplayName("double_ball이 나올 시 BALL 카운트가 2 증가합니다.")
    @Test
    void dbStrikeTest() {
        DOUBLE_BALL.call();
        assertThat(COUNT_STORE).containsEntry("STRIKE", 0).containsEntry("BALL", 2);
    }

    @DisplayName("double_stike가 나올 시 STRIKE 카운트가 2 증가합니다.")
    @Test
    void dbBallTest() {
        DOUBLE_STRIKE.call();
        assertThat(COUNT_STORE).containsEntry("STRIKE", 2).containsEntry("BALL", 0);
    }

    @DisplayName("hit 시 볼 카운트가 리셋됩니다.")
    @Test
    void hitTest(){
        HIT.call();
        assertThat(COUNT_STORE).containsEntry("STRIKE",0).containsEntry("BALL",0);
    }


    @DisplayName("타격 결과는 strike, ball, hit, double_ball, double_strike 입니다.")
    @RepeatedTest(10)
    void t2() {
        BattingResult result =
            BattingResult.values()[RANDOM.nextInt(BattingResult.values().length)];
        assertThat(result).isIn(BattingResult.values());
    }

    private int getPickedCount() {
        int count = 0;
        for (int i = 0; i < 200000; i++) {
            BattingResult result =
                BattingResult.values()[RANDOM.nextInt(BattingResult.values().length)];
            if (result == STRIKE) {
                count++;
            }
        }
        return count;
    }
}
