package com.hyunec.cosmicbaseballinit.acceptancetest;

import static com.hyunec.cosmicbaseballinit.domain.BattingResult.BALL;
import static com.hyunec.cosmicbaseballinit.domain.BattingResult.HIT;
import static com.hyunec.cosmicbaseballinit.domain.BattingResult.STRIKE;
import static org.assertj.core.api.Assertions.assertThat;

import com.hyunec.cosmicbaseballinit.domain.BattingResult;
import java.util.Random;
import org.assertj.core.data.Percentage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;

class NormalBaseballLv1Test {

    private Random random = RandomFactory.getInstance();

    @DisplayName("타격 결과는 모두 같은 확률을 가집니다.")
    @RepeatedTest(10)
    void t1() {
        BattingResult battingResult = getResult();
        int result = getBattingResultCount(battingResult.getIndex());

        assertThat(result).isCloseTo(33_300, Percentage.withPercentage(1));
    }

    @DisplayName("타격 결과는 strike, ball, hit 입니다.")
    @RepeatedTest(10)
    void t2() {
        assertThat(getResult()).isBetween(STRIKE, BALL);
    }

    private int getBattingResultCount(int resultNumber) {
        int count = 0;
        for (int i = 0; i < 100_000; i++) {
            int randomNumber = random.nextInt(BattingResult.values().length);
            count = addCount(resultNumber, count, randomNumber);
        }
        return count;
    }

    private int addCount(final int resultNumber, int count, final int randomNumber) {
        if (resultNumber == randomNumber) {
            count++;
        }
        return count;
    }

    private BattingResult getResult() {
        switch (random.nextInt(BattingResult.values().length)) {
            case 0:
                return STRIKE;
            case 1:
                return HIT;
            default:
                return BALL;
        }
    }
}
