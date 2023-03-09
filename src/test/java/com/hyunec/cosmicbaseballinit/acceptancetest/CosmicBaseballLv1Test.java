package com.hyunec.cosmicbaseballinit.acceptancetest;

import static com.hyunec.cosmicbaseballinit.service.BattingService.RANDOM;
import static org.assertj.core.api.Assertions.assertThat;

import com.hyunec.cosmicbaseballinit.domain.Ball;
import com.hyunec.cosmicbaseballinit.domain.BattingResult;
import com.hyunec.cosmicbaseballinit.domain.BattingResultCount;
import com.hyunec.cosmicbaseballinit.domain.DoubleBall;
import com.hyunec.cosmicbaseballinit.domain.DoubleStrike;
import com.hyunec.cosmicbaseballinit.domain.Hit;
import com.hyunec.cosmicbaseballinit.domain.Strike;
import com.hyunec.cosmicbaseballinit.service.BattingResultGenerator;
import java.util.List;
import org.assertj.core.data.Percentage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;


class CosmicBaseballLv1Test {

    private final List<BattingResult> battingResults =
        BattingResultGenerator.of(
            new Strike(), new Ball(), new Hit(), new DoubleStrike(), new DoubleBall()
        );
    private final BattingResult strike = battingResults.get(0);
    private final BattingResult ball = battingResults.get(1);
    private final BattingResult hit = battingResults.get(2);
    private final BattingResult doubleStrike = battingResults.get(3);
    private final BattingResult doubleBall = battingResults.get(4);

    private BattingResultCount container;

    @BeforeEach
    void init() {
        container = BattingResultCount.getInstance();
    }

    @DisplayName("타격 결과는 모두 같은 확률을 가집니다.")
    @RepeatedTest(10)
    void t1() {
        assertThat(getTotalStrikeCount()).isCloseTo(20_000, Percentage.withPercentage(1));
    }

    @DisplayName("strike 시 strike 카운트가 1 증가합니다.")
    @Test
    void strikeTest() {
        container.addCount(strike);

        assertThat(container.getStrikeCount()).isEqualTo(1);
    }

    @DisplayName("ball 시 ball 카운트가 1 증가합니다.")
    @Test
    void ballTest() {
        container.addCount(ball);

        assertThat(container.getBallCount()).isEqualTo(1);
    }

    @DisplayName("double strike 시 strike 카운트가 2 증가합니다.")
    @Test
    void doubleStrikeTest() {
        container.addCount(doubleStrike);

        assertThat(container.getStrikeCount()).isEqualTo(2);
    }

    @DisplayName("타격 결과는 strike, ball, hit, double_ball, double_strike 입니다.")
    @RepeatedTest(10)
    void t2() {
        BattingResult result = battingResults.get(RANDOM.nextInt(battingResults.size()));
        assertThat(result).isIn(doubleBall, doubleStrike, ball, strike, hit);
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
