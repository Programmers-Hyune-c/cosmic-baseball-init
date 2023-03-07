package com.hyunec.cosmicbaseballinit.acceptancetest;

import static com.hyunec.cosmicbaseballinit.service.BattingService.BATTING_RESULTS;
import static com.hyunec.cosmicbaseballinit.service.BattingService.RANDOM;
import static org.assertj.core.api.Assertions.assertThat;

import com.hyunec.cosmicbaseballinit.domain.BattingResult;
import org.assertj.core.data.Percentage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;

class NormalBaseballLv1Test {

    @DisplayName("타격 결과는 모두 같은 확률을 가집니다.")
    @RepeatedTest(10)
    void t1() {
        assertThat(getTotalRandomPick()).isCloseTo(33_300, Percentage.withPercentage(1));
    }

    @DisplayName("타격 결과는 strike, ball, hit 입니다.")
    @RepeatedTest(10)
    void t2() {
        BattingResult result = BATTING_RESULTS.get(RANDOM.nextInt(BATTING_RESULTS.size()));
        assertThat(result).
                        isIn(BATTING_RESULTS.get(0), BATTING_RESULTS.get(1), BATTING_RESULTS.get(2));
    }

    private int getTotalRandomPick() {
        int count = 0;
        for (int i = 0; i < 100000; i++) {
            BattingResult result = BATTING_RESULTS.get(RANDOM.nextInt(BATTING_RESULTS.size()));
            if (result == BATTING_RESULTS.get(0)) {
                count++;
            }
        }
        return count;
    }
}
