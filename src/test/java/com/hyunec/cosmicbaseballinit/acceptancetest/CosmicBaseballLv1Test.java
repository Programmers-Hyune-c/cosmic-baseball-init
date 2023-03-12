package com.hyunec.cosmicbaseballinit.acceptancetest;

import static com.hyunec.cosmicbaseballinit.domain.BattingResult.BALL;
import static com.hyunec.cosmicbaseballinit.domain.BattingResult.DOUBLE_BALL;
import static com.hyunec.cosmicbaseballinit.domain.BattingResult.DOUBLE_STRIKE;
import static com.hyunec.cosmicbaseballinit.domain.BattingResult.HIT;
import static com.hyunec.cosmicbaseballinit.domain.BattingResult.STRIKE;
import static org.assertj.core.api.Assertions.assertThat;

import com.hyunec.cosmicbaseballinit.domain.BattingResult;
import com.hyunec.cosmicbaseballinit.domain.TotalBattingResult;
import org.assertj.core.data.Percentage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

@Disabled
class CosmicBaseballLv1Test {

    private TotalBattingResult container;

    @BeforeEach
    void init() {
        container = TotalBattingResult.getInstance();
    }

    @DisplayName("타격 결과는 모두 같은 확률을 가집니다.")
    @RepeatedTest(10)
    void t1() {
        assertThat(getTotalStrikeCount()).isCloseTo(20_000, Percentage.withPercentage(1));
    }

    @DisplayName("strike 시 strike 카운트가 1 증가합니다.")
    @Test
    void strikeTest() {
        container.setBattingTotalResult(STRIKE);

        assertThat(container.getStrikeCount()).isEqualTo(1);
    }

    @DisplayName("ball 시 ball 카운트가 1 증가합니다.")
    @Test
    void ballTest() {
        container.setBattingTotalResult(BALL);

        assertThat(container.getBallCount()).isEqualTo(1);
    }

    @DisplayName("double strike 시 strike 카운트가 2 증가합니다.")
    @Test
    void doubleStrikeTest() {
        container.setBattingTotalResult(DOUBLE_BALL);

        assertThat(container.getStrikeCount()).isEqualTo(2);
    }

    @DisplayName("double ball 시 ball 카운트가 2 증가합니다.")
    @Test
    void doubleBallTest() {
        container.setBattingTotalResult(DOUBLE_BALL);

        assertThat(container.getStrikeCount()).isEqualTo(2);
    }

    @DisplayName("타격 결과는 strike, ball, hit, double_ball, double_strike 입니다.")
    @RepeatedTest(10)
    void t2() {
        BattingResult result =BattingResult.values()[BattingResult.values().length];
        assertThat(result).isIn(DOUBLE_STRIKE, DOUBLE_BALL, BALL, STRIKE, HIT);
    }

    private int getTotalStrikeCount() {
        int count = 0;
        for (int i = 0; i < 100000; i++) {
            BattingResult result =BattingResult.values()[BattingResult.values().length];
            if (result == STRIKE) {
                count++;
            }
        }
        return count;
    }

}
