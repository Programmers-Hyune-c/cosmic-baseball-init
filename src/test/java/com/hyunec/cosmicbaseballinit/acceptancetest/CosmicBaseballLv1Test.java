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
class CosmicBaseballLv1Test {

    @Autowired
    private BattingServiceImpl battingServiceImpl;

    @DisplayName("타격 결과는 모두 같은 확률을 가집니다.")
    @RepeatedTest(100)
    void t1() {
        int caseSize = 100000;
        int count = 0;

        BattingResult expected = battingServiceImpl.batting();

        for (int i = 0; i < caseSize; i++) {
            BattingResult result = battingServiceImpl.batting();
            if (expected == result) {
                count++;
            }
        }

        assertThat(count).isCloseTo(caseSize / 5, Percentage.withPercentage(5));
    }

    @DisplayName("타격 결과는 strike, ball, hit, double_ball, double_strike 입니다.")
    @Test
    void t2() {
        BattingResult result = battingServiceImpl.batting();

        List<BattingResult> validResult = Stream.of(STRIKE, BALL, HIT, DOUBLE_STRIKE, DOUBLE_BALL).collect(Collectors.toList());

        assertThat(result)
                .isIn(validResult);
    }
}
