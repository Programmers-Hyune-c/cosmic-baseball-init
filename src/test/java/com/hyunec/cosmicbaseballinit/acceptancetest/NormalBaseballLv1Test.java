package com.hyunec.cosmicbaseballinit.acceptancetest;

import static com.hyunec.cosmicbaseballinit.service.BattingResult.BALL;
import static com.hyunec.cosmicbaseballinit.service.BattingResult.HIT;
import static com.hyunec.cosmicbaseballinit.service.BattingResult.STRIKE;
import static org.assertj.core.api.Assertions.assertThat;

import com.hyunec.cosmicbaseballinit.service.Batter;
import com.hyunec.cosmicbaseballinit.service.BattingResult;
import java.util.Random;
import org.assertj.core.data.Percentage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

class NormalBaseballLv1Test {

    static Batter batter;
    static Random random;

    @BeforeAll
    static void init() {
        random = new Random();
        batter = new Batter(random);
    }

    @DisplayName("타격 결과는 모두 같은 확률을 가집니다.")
    @RepeatedTest(10)
    void t1() {
        BattingResult battingResult = batter.hit();
        int count = 0;
        count = getBattingResultCount(battingResult.ordinal(), count);

        assertThat(count).isCloseTo(33_300, Percentage.withPercentage(1));
    }


    @DisplayName("타격 결과는 strike, ball, hit 입니다.")
    @Test
    void t2() {
        BattingResult result = batter.hit();
        assertThat(result).isIn(STRIKE, HIT, BALL);
    }


    private int getBattingResultCount(int resultNumber, int count) {
        for (int i = 0; i < 100_000; i++) {
            int randomNumber = random.nextInt(3);
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

}
