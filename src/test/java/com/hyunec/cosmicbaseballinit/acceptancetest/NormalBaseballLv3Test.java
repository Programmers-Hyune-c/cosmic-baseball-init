package com.hyunec.cosmicbaseballinit.acceptancetest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.hyunec.cosmicbaseballinit.domain.ScoreAndBaseBoard;
import com.hyunec.cosmicbaseballinit.domain.TotalBattingResult;
import com.hyunec.cosmicbaseballinit.exception.BusinessException;
import com.hyunec.cosmicbaseballinit.exception.ExceptionType;
import java.util.Random;
import org.assertj.core.data.Percentage;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class NormalBaseballLv3Test {

    @Disabled("확률 테스트 메서드는 로컬 환경에서 독립적으로 실행합니다.")
    @DisplayName("타격 결과 확률을 가변적으로 입력받을 수 있습니다.")
    @ParameterizedTest
    @ValueSource(ints = {0, 5, 100})
    void t1(int percent) {
        int count = getRandomStrikeCount(percent);
        assertThat(count).isCloseTo(percent * 10_000, Percentage.withPercentage(1));
    }

    @DisplayName("1개의 게임은 1개의 회로 이루어져 있으며, 1개의 회는 3 out 이 되면 종료됩니다.")
    @Test
    void t2() {
        TotalBattingResult result = new TotalBattingResult(0, 0, 3);
        assertThatThrownBy(() -> {
            endGame(result);
        })
            .isInstanceOf(BusinessException.class)
            .hasMessageContaining(ExceptionType.END_INNING.getMessage());
    }

    @DisplayName("한 명의 타자가 진루 시 OnBaseList에 1(baseNumber)이 있습니다.")
    @Test
    void t3() {
        ScoreAndBaseBoard scoreAndBaseBoard = new ScoreAndBaseBoard();

        scoreAndBaseBoard.adjustBaseAndScore();

        assertThat(scoreAndBaseBoard.getOnBaseList()).containsExactly(1);

    }

    @DisplayName("두 명의 타자가 진루 시 OnBaseList에 1과 2가(baseNumber)이 있습니다.")
    @Test
    void t4() {
        ScoreAndBaseBoard scoreAndBaseBoard = new ScoreAndBaseBoard(1);

        scoreAndBaseBoard.adjustBaseAndScore();

        assertThat(scoreAndBaseBoard.getOnBaseList()).containsExactly(1,2);

    }

    @DisplayName("득점을 표현할 수 있습니다.")
    @Test
    void t5() {
        ScoreAndBaseBoard scoreAndBaseBoard = new ScoreAndBaseBoard(3);

        scoreAndBaseBoard.adjustBaseAndScore();

        assertThat(scoreAndBaseBoard.getScore()).isEqualTo(1);
    }

    private int getRandomStrikeCount(int percent) {
        int count = 0;
        for (int i = 0; i < 1_000_000; i++) {
            int bound = 100;
            if (new Random().nextInt(bound) < percent) {
                count++;
            }
        }
        return count;
    }

    private void endGame(TotalBattingResult result) {
        if (result.getOutCount() >= 3) {
            throw new BusinessException(ExceptionType.END_INNING);
        }
    }
}
