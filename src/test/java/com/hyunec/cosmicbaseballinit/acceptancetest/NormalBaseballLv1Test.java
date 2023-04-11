package com.hyunec.cosmicbaseballinit.acceptancetest;

import com.hyunec.cosmicbaseballinit.model.BattingResult;
import com.hyunec.cosmicbaseballinit.service.BattingServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class NormalBaseballLv1Test {

    @Autowired
    private BattingServiceImpl battingServiceImpl;

    @DisplayName("타격 결과는 모두 같은 확률을 가집니다.")
    @Test
    void t1() {

        double strikeCount = 0;
        double ballCount = 0;
        double hitCount = 0;

        double caseSize = 150000;

        for (double i = 0; i < caseSize; i++) {
            BattingResult result = battingServiceImpl.batting();

            switch (result) {
                case STRIKE:
                    strikeCount += 1;
                    break;
                case BALL:
                    ballCount += 1;
                    break;
                case HIT:
                    hitCount += 1;
                    break;
            }
        }

        double strikeProbability = Math.round(strikeCount / caseSize * 10) / 10F;
        double ballProbability = Math.round(ballCount / caseSize * 10) / 10F;
        double hitProbability = Math.round(hitCount / caseSize * 10) / 10F;

        Assertions.assertTrue(strikeProbability == ballProbability && ballProbability == hitProbability);
    }

    @DisplayName("타격 결과는 strike, ball, hit 입니다.")
    @Test
    void t2() {

        BattingResult result = battingServiceImpl.batting();

        boolean validResult = result.equals(BattingResult.HIT)
                || result.equals(BattingResult.STRIKE)
                || result.equals(BattingResult.BALL);

        Assertions.assertTrue(validResult);
    }
}
