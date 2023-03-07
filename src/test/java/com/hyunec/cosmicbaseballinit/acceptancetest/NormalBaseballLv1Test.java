package com.hyunec.cosmicbaseballinit.acceptancetest;

import static com.hyunec.cosmicbaseballinit.service.BattingService.RANDOM;
import static org.assertj.core.api.Assertions.assertThat;

import com.hyunec.cosmicbaseballinit.domain.Ball;
import com.hyunec.cosmicbaseballinit.domain.BattingResult;
import com.hyunec.cosmicbaseballinit.domain.Hit;
import com.hyunec.cosmicbaseballinit.domain.Strike;
import java.util.ArrayList;
import java.util.List;
import org.assertj.core.data.Percentage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;

class NormalBaseballLv1Test {

    private final List<BattingResult> battingResults =
                        new ArrayList<>(List.of(new Strike(), new Hit(), new Ball()));
    private final BattingResult strike = battingResults.get(0);
    private final BattingResult hit = battingResults.get(1);
    private final BattingResult ball = battingResults.get(2);

    @DisplayName("타격 결과는 모두 같은 확률을 가집니다.")
    @RepeatedTest(10)
    void t1() {
        assertThat(getTotalStrikeCount()).isCloseTo(33_300, Percentage.withPercentage(1));
    }

    @DisplayName("타격 결과는 strike, ball, hit 입니다.")
    @RepeatedTest(10)
    void t2() {
        BattingResult result = battingResults.get(RANDOM.nextInt(battingResults.size()));
        assertThat(result).isIn(strike, hit, ball);
    }

    private int getTotalStrikeCount() {
        int count = 0;
        for (int i = 0; i < 100000; i++) {
            BattingResult result = battingResults.get(RANDOM.nextInt(battingResults.size()));
            if (result == strike) {
                count++;
            }
        }
        return count;
    }
}
