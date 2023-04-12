package com.hyunec.cosmicbaseballinit.acceptancetest;

import com.hyunec.cosmicbaseballinit.model.BattingResult;
import com.hyunec.cosmicbaseballinit.service.BattingServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.hyunec.cosmicbaseballinit.model.BattingResult.*;
import static org.assertj.core.api.Assertions.assertThat;

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

        assertThat(strikeProbability == ballProbability && ballProbability == hitProbability)
                .isTrue();
    }

    @DisplayName("타격 결과는 strike, ball, hit 입니다.")
    @Test
    void t2() {

        BattingResult result = battingServiceImpl.batting();

        List<BattingResult> validResult = Stream.of(STRIKE, BALL, HIT).collect(Collectors.toList());

        assertThat(result)
                .isIn(validResult);
    }
}
