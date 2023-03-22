package com.hyunec.cosmicbaseballinit.acceptancetest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

import com.hyunec.cosmicbaseballinit.domain.ScoreBoard;
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

    @DisplayName("1명의 타자가 진루 시 1루 베이스가 true로 변합니다.")
    @Test
    void t3() {
        ScoreBoard scoreBoard = ScoreBoard.of(false, false, false);

        scoreBoard.adjustBaseAndScore();
        boolean firstBase = scoreBoard.isInFirstBase();
        boolean secondBase = scoreBoard.isInSecondBase();
        boolean thirdBase = scoreBoard.isInThirdBase();

        assertSoftly(soft -> {
            soft.assertThat(firstBase).isTrue();
            soft.assertThat(secondBase).isFalse();
            soft.assertThat(thirdBase).isFalse();
        });
    }

    @DisplayName("2명의 타자가 진루시 1루, 2루 베이스가 true로 변합니다.")
    @Test
    void t4() {
        ScoreBoard scoreBoard = ScoreBoard.of(true, false, false);

        scoreBoard.adjustBaseAndScore();
        boolean firstBase = scoreBoard.isInFirstBase();
        boolean secondBase = scoreBoard.isInSecondBase();
        boolean thirdBase = scoreBoard.isInThirdBase();

        assertSoftly(soft -> {
            soft.assertThat(firstBase).isTrue();
            soft.assertThat(secondBase).isTrue();
            soft.assertThat(thirdBase).isFalse();
        });
    }

    @DisplayName("3명의 타자가 진루시 1루, 2루, 3루 베이스가 true로 변합니다..")
    @Test
    void t() {
        ScoreBoard scoreBoard = ScoreBoard.of(true, true, false);

        scoreBoard.adjustBaseAndScore();
        boolean firstBase = scoreBoard.isInFirstBase();
        boolean secondBase = scoreBoard.isInSecondBase();
        boolean thirdBase = scoreBoard.isInThirdBase();

        assertSoftly(soft -> {
            soft.assertThat(firstBase).isTrue();
            soft.assertThat(secondBase).isTrue();
            soft.assertThat(thirdBase).isTrue();
        });
    }

    @DisplayName("득점을 표현할 수 있습니다.")
    @Test
    void t5() {
        ScoreBoard scoreBoard = ScoreBoard.of(true, true, true);

        scoreBoard.adjustBaseAndScore();
        int score = scoreBoard.getScore();

        assertThat(score).isEqualTo(1);
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
