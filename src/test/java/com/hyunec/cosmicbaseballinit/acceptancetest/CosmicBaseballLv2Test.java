package com.hyunec.cosmicbaseballinit.acceptancetest;

import static com.hyunec.cosmicbaseballinit.domain.BatterStatus.GO_TO_BASE;
import static com.hyunec.cosmicbaseballinit.domain.BatterStatus.OUT;
import static com.hyunec.cosmicbaseballinit.domain.BattingResult.BALL;
import static com.hyunec.cosmicbaseballinit.domain.BattingResult.BULL_EYE_BALL;
import static com.hyunec.cosmicbaseballinit.domain.BattingResult.BULL_EYE_STRIKE;
import static com.hyunec.cosmicbaseballinit.domain.BattingResult.STRIKE;
import static com.hyunec.cosmicbaseballinit.service.BattingService.RANDOM;
import static org.assertj.core.api.Assertions.assertThat;

import com.hyunec.cosmicbaseballinit.domain.BattingResult;
import com.hyunec.cosmicbaseballinit.domain.TotalBattingResult;
import org.assertj.core.data.Percentage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

class CosmicBaseballLv2Test {

    private static final int FREQUENCY = 5_000_000;

    @DisplayName("strike 의 20% 는 bullseye_strike 입니다.")
    @RepeatedTest(100)
    void t1() {
        int totalStrikeCount = getTotalStrikeCount();
        int bullsEyeStrikeCount = getTotalBullEyeStrikeCount();

        assertThat(bullsEyeStrikeCount).isCloseTo(
                                                    (int) (totalStrikeCount * 0.2),
                                                    Percentage.withPercentage(1)
        );
    }


    @DisplayName("ball 의 20% 는 bullseye_ball 입니다.")
    @RepeatedTest(10)
    void t2() {
        int totalBallCount = getTotalBallCount();
        int bullsEyeBallCount = getTotalBullEyeBallCount();

        assertThat(bullsEyeBallCount).isCloseTo(
                                                    (int) (totalBallCount * 0.2),
                                                    Percentage.withPercentage(1)
        );
    }

    @DisplayName("타격 결과가 bullseye_strike 이면 타석 결과는 out 입니다.")
    @Test
    void t3() {
        TotalBattingResult totalBattingResult = new TotalBattingResult();
        totalBattingResult.addBattingResultCount(BULL_EYE_STRIKE);
        totalBattingResult.judgeBatterStatus();
        assertThat(totalBattingResult.getBatterStatus()).isEqualTo(OUT);
    }

    @DisplayName("타격 결과가 bullseye_ball 이면 타석 결과는 four_ball 으로 진루 입니다.")
    @Test
    void t4() {
        TotalBattingResult totalBattingResult = new TotalBattingResult();
        totalBattingResult.addBattingResultCount(BULL_EYE_BALL);
        totalBattingResult.judgeBatterStatus();
        assertThat(totalBattingResult.getBatterStatus()).isEqualTo(GO_TO_BASE);
    }


    private int getTotalStrikeCount() {
        int count = 0;
        for (int i = 0; i < FREQUENCY; i++) {
            BattingResult result =
                BattingResult.values()[RANDOM.nextInt(BattingResult.values().length)];
            if (result == STRIKE) {
                count++;
            }
        }
        return count;
    }


    private int getTotalBullEyeStrikeCount() {
        int count = 0;
        for (int i = 0; i < FREQUENCY; i++) {
            BattingResult result =
                BattingResult.values()[RANDOM.nextInt(BattingResult.values().length)];
            if (result == STRIKE) {
                int index = RANDOM.nextInt(5);
                if (index == BULL_EYE_STRIKE.ordinal()) {
                    count++;
                }
            }
        }
        return count;
    }

    private int getTotalBallCount() {
        int count = 0;
        for (int i = 0; i < FREQUENCY; i++) {
            BattingResult result =
                BattingResult.values()[RANDOM.nextInt(BattingResult.values().length)];
            if (result == BALL) {
                count++;
            }
        }
        return count;
    }

    private int getTotalBullEyeBallCount() {
        int count = 0;
        for (int i = 0; i < FREQUENCY; i++) {
            BattingResult result =
                BattingResult.values()[RANDOM.nextInt(BattingResult.values().length)];

            if (result == BALL) {
                int index = RANDOM.nextInt(5);
                if (index == BULL_EYE_BALL.ordinal()) {
                    count++;
                }
            }
        }
        return count;
    }
}
