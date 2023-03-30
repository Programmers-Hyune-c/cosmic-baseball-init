package com.hyunec.cosmicbaseballinit.acceptancetest;

import static com.hyunec.cosmicbaseballinit.domain.BattingResult.HIT;
import static org.assertj.core.api.Assertions.assertThat;

import com.hyunec.cosmicbaseballinit.domain.BaseList;
import com.hyunec.cosmicbaseballinit.domain.BattingResult;
import com.hyunec.cosmicbaseballinit.domain.FeverScoreBoard;
import com.hyunec.cosmicbaseballinit.util.RandomBattingResultGenerator;
import java.util.List;
import org.assertj.core.data.Percentage;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.EnumSource.Mode;


class CosmicBaseballLv3Test {

    @Disabled("확률 테스트 메서드는 로컬 환경에서 독립적으로 실행합니다.")
    @DisplayName("fever inning의 안타 확률은 normal inning의 안타 확률의 2배입니다.")
    @ParameterizedTest
    @EnumSource(
        value = BattingResult.class,
        mode = Mode.EXCLUDE,
        names = {"BULL_EYE_STRIKE", "BULL_EYE_BALL"}
    )
    void t1(BattingResult battingResult) {
        int hitCount = getHitCount(battingResult);
        int hitCountInFeverInning = getHitCountInFeverInning(battingResult);
        assertThat(hitCountInFeverInning).isCloseTo(hitCount * 2, Percentage.withPercentage(1));
    }
    @DisplayName("3루가 없어집니다.")
    @Test
    void t2() {
        FeverScoreBoard feverScoreBoard = new FeverScoreBoard(new BaseList(List.of(1, 2)));

        feverScoreBoard.adjust(HIT);

        assertThat(feverScoreBoard.getScore()).isEqualTo(1);
    }

    private int getHitCount(BattingResult target) {
        int count = 0;
        int percentage = 20;
        for (int i = 0; i < 1000000; i++) {
            BattingResult result = RandomBattingResultGenerator.get(percentage, target);
            if (result == HIT) {
                count++;
            }
        }
        return count;
    }

    private int getHitCountInFeverInning(BattingResult target) {
        int count = 0;
        int percentage = 20;
        for (int i = 0; i < 1000000; i++) {
            BattingResult resultOnFever =
                RandomBattingResultGenerator.getOnFever(percentage, target);
            if (resultOnFever == HIT) {
                count++;
            }
        }
        return count;
    }
}
