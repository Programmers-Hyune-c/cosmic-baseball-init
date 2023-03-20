package com.hyunec.cosmicbaseballinit.acceptancetest;

import static com.hyunec.cosmicbaseballinit.domain.BatterStatus.GO_TO_BASE;
import static com.hyunec.cosmicbaseballinit.domain.BatterStatus.OUT;
import static com.hyunec.cosmicbaseballinit.domain.BattingResult.BALL;
import static com.hyunec.cosmicbaseballinit.domain.BattingResult.BULL_EYE_BALL;
import static com.hyunec.cosmicbaseballinit.domain.BattingResult.BULL_EYE_STRIKE;
import static com.hyunec.cosmicbaseballinit.domain.BattingResult.STRIKE;
import static org.assertj.core.api.Assertions.assertThat;

import com.hyunec.cosmicbaseballinit.domain.BattingResult;
import com.hyunec.cosmicbaseballinit.domain.TotalBattingResult;
import java.util.Random;
import org.assertj.core.data.Percentage;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CosmicBaseballLv2Test {

    private static final int FREQUENCY = 1_000_000;

    @Disabled("확률 테스트 메서드는 로컬 환경에서만 단독으로 테스트합니다.")
    @DisplayName("strike 의 20% 는 bullseye_strike 입니다.")
    @Test
    void t1() {
        int totalStrikeCount = getTotalBattingResultCount(STRIKE);
        int bullsEyeStrikeCount = getTotalBullEyeResultCount(STRIKE);

        assertThat(bullsEyeStrikeCount).isCloseTo(
            (int) (totalStrikeCount * 0.2),
            Percentage.withPercentage(0.4)
        );
    }

    @Disabled("확률 테스트 메서드는 로컬 환경에서만 단독으로 테스트합니다.")
    @DisplayName("ball 의 20% 는 bullseye_ball 입니다." )
    @Test
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
        TotalBattingResult totalBattingResult = TotalBattingResult.of(0, 0);
        totalBattingResult.adjustAccordingToBattingResult(BULL_EYE_STRIKE);

        assertThat(totalBattingResult.getBatterStatus()).isEqualTo(OUT);
    }

    @DisplayName("타격 결과가 bullseye_ball 이면 타석 결과는 four_ball 으로 진루 입니다.")
    @Test
    void t4() {
        TotalBattingResult totalBattingResult = TotalBattingResult.of(0, 0);
        totalBattingResult.adjustAccordingToBattingResult(BULL_EYE_BALL);

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
            if (getRandomBattingResult() == battingResult
                && getRandomBattingResult() == battingResult) {
                count++;
            }
        }
        return count;
    }

    private BattingResult getRandomBattingResult() {
        int noBullEyeResultRange = 5;
        return BattingResult.values()[new Random().nextInt(noBullEyeResultRange)];
    }
}
