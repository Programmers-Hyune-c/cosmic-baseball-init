package com.hyunec.cosmicbaseballinit.acceptancetest;

import com.hyunec.cosmicbaseballinit.model.BattingResult;
import com.hyunec.cosmicbaseballinit.service.BattingServiceImpl;
import org.assertj.core.data.Percentage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
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
    @RepeatedTest(100)
    void t1() {

        double caseSize = 100000;
        double count = 0;

        BattingResult randomResult = battingServiceImpl.batting();

        for (double i = 0; i < caseSize; i++) {
            BattingResult result = battingServiceImpl.batting();
            if (randomResult == result) {
                count++;
            }
        }

        assertThat(count).isCloseTo(33333, Percentage.withPercentage(5));
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
