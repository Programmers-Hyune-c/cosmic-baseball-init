package com.hyunec.cosmicbaseballinit.acceptancetest;

import static com.hyunec.cosmicbaseballinit.domain.BatterStatus.GO_TO_BASE;
import static com.hyunec.cosmicbaseballinit.domain.BatterStatus.OUT;
import static com.hyunec.cosmicbaseballinit.domain.BattingResult.BALL;
import static com.hyunec.cosmicbaseballinit.domain.BattingResult.BULL_EYE_BALL;
import static com.hyunec.cosmicbaseballinit.domain.BattingResult.BULL_EYE_STRIKE;
import static com.hyunec.cosmicbaseballinit.domain.BattingResult.STRIKE;
import static com.hyunec.cosmicbaseballinit.service.RandomBattingResultGenerator.RANDOM;
import static org.assertj.core.api.Assertions.assertThat;

import com.hyunec.cosmicbaseballinit.domain.BattingResult;
import com.hyunec.cosmicbaseballinit.domain.TotalBattingResult;
import org.assertj.core.data.Percentage;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

class CosmicBaseballLv2Test {

    private static final int FREQUENCY = 2_000_000;

    @Disabled("CI 시 확률 테스트는 ignore 합니다.")
    @DisplayName("strike 의 20% 는 bullseye_strike 입니다.")
    @RepeatedTest(10)
    void t1() {
        int totalStrikeCount = getTotalBattingResultCount(STRIKE);
        int bullsEyeStrikeCount = getTotalBullEyeResultCount(STRIKE);

        assertThat(bullsEyeStrikeCount).isCloseTo(
                                                    (int) (totalStrikeCount * 0.2),
                                                    Percentage.withPercentage(1)
        );
    }


    @Disabled("CI 시 확률 테스트는 ignore 합니다.")
    @DisplayName("ball 의 20% 는 bullseye_ball 입니다.")
    @RepeatedTest(10)
    void t2() {
        int totalBallCount = getTotalBattingResultCount(BALL);
        int bullsEyeBallCount = getTotalBullEyeResultCount(BALL);

        assertThat(bullsEyeBallCount).isCloseTo(
                                                    (int) (totalBallCount * 0.2),
                                                    Percentage.withPercentage(1)
        );
    }

    @DisplayName("타격 결과가 bullseye_strike 이면 타석 결과는 out 입니다.")
    @Test
    void t3() {
        TotalBattingResult totalBattingResult = new TotalBattingResult();

        totalBattingResult.setBattingResult(BULL_EYE_STRIKE);
        totalBattingResult.increaseBattingResultCount();
        totalBattingResult.judgeBatterStatus();

        assertThat(totalBattingResult.getBatterStatus()).isEqualTo(OUT);
    }

    @DisplayName("타격 결과가 bullseye_ball 이면 타석 결과는 four_ball 으로 진루 입니다.")
    @Test
    void t4() {
        TotalBattingResult totalBattingResult = new TotalBattingResult();

        totalBattingResult.setBattingResult(BULL_EYE_BALL);
        totalBattingResult.increaseBattingResultCount();
        totalBattingResult.judgeBatterStatus();

        assertThat(totalBattingResult.getBatterStatus()).isEqualTo(GO_TO_BASE);
    }

    private int getTotalBattingResultCount(BattingResult battingResult) {
        int count = 0;
        for (int i = 0; i < FREQUENCY; i++) {
            if (getRandomBattingResult() == battingResult) {
                count++;
            }
        }
        return count;
    }

    private int getTotalBullEyeResultCount(BattingResult battingResult) {
        int count = 0;
        for (int i = 0; i < FREQUENCY; i++) {
            if (getRandomBattingResult() == battingResult && getRandomBattingResult() == battingResult ) {
                    count++;
            }
        }
        return count;
    }
    private BattingResult getRandomBattingResult() {
        int noBullEyeResultRange = 5;
        return BattingResult.values()[RANDOM.nextInt(noBullEyeResultRange)];
    }
}
