package com.hyunec.cosmicbaseballinit.acceptancetest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import com.hyunec.cosmicbaseballinit.domain.baseball.model.Batting;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

class NormalBaseballLv1Test {

    @DisplayName("strike, ball, hit 는 같은 확률 입니다.")
    @RepeatedTest(value = 100)
    void t1() {
        // Given
        // 3만번 시행시 deviation 이 5% 미만인지 체크한다. 테스트시 strike, ball, hit 가 확률수렴한다
        int tc = 3 * 10000;
        double deviation = 0.05;

        // When
        int total = 0;
        for (int i = 0; i < tc; i++) {
            total += Batting.generate().ordinal(); // 1회 배팅 결과를 만드는 메서드를 테스트
        }

        int lowerBound = (int) Math.floor((double) tc * (1.0 - deviation));
        int upperBound = (int) Math.floor((double) tc * (1.0 + deviation));

        // Then
        System.out.println(total);
        assertThat(total).isBetween(lowerBound, upperBound);
    }

    @DisplayName("3B 타석에서 타격 결과가 ball 이면 타석 결과는 four_ball 됩니다.")
    @Test
    void t2() {
        throw new RuntimeException("Not yet implemented");
    }

    @DisplayName("2S 타석에서 타격 결과가 strike 이면 타석 결과는 out 됩니다.")
    @Test
    void t3() {
        throw new RuntimeException("Not yet implemented");
    }

    @DisplayName("진행 중인 타석이 있는 상태에서 새로운 타석을 진행할 수 없습니다.")
    @Test
    void t4() {
        throw new RuntimeException("Not yet implemented");
    }

    @DisplayName("타석이 종료되면 초기화하여 새로 진행할 수 있습니다.")
    @Test
    void t5() {
        throw new RuntimeException("Not yet implemented");
    }
}
