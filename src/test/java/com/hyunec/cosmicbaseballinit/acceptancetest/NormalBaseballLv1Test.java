package com.hyunec.cosmicbaseballinit.acceptancetest;

import static com.hyunec.cosmicbaseballinit.service.BattingResult.BALL;
import static com.hyunec.cosmicbaseballinit.service.BattingResult.HIT;
import static com.hyunec.cosmicbaseballinit.service.BattingResult.STRIKE;
import static org.assertj.core.api.Assertions.assertThat;

import com.hyunec.cosmicbaseballinit.service.BattingResult;
import com.hyunec.cosmicbaseballinit.util.RandomFactory;
import java.util.Random;
import org.assertj.core.data.Percentage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

class NormalBaseballLv1Test extends ResultCount {

    @DisplayName("타격 결과는 모두 같은 확률을 가집니다.")
    @RepeatedTest(10)
    void t1() {
        Random random = RandomFactory.getInstance();
        BattingResult battingResult = getResult(random);
        int count = 0;
        int enumCount = 3;
        count = super.getBattingResultCount(battingResult.getIndex(), count, random, enumCount);

        assertThat(count).isCloseTo(33_300, Percentage.withPercentage(1));
    }

    @DisplayName("타격 결과는 strike, ball, hit 입니다.")
    @Test
    void t2() {
        Random random = RandomFactory.getInstance();
        BattingResult result = getResult(random);
        assertThat(result).isIn(STRIKE, HIT, BALL);
    }

    private BattingResult getResult(Random random) {
        switch (random.nextInt(3)) {
            case 0:
                return STRIKE;
            case 1:
                return HIT;
            default:
                return BALL;
        }
    }
}
