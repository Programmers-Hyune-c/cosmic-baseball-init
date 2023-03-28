package com.hyunec.cosmicbaseballinit.acceptancetest;

import static com.hyunec.cosmicbaseballinit.domain.BattingResult.HIT;
import static org.assertj.core.api.Assertions.assertThat;

import com.hyunec.cosmicbaseballinit.domain.BattingResult;
import com.hyunec.cosmicbaseballinit.domain.ScoreBoard;
import com.hyunec.cosmicbaseballinit.service.RandomBattingResultGenerator;
import java.util.List;
import org.assertj.core.data.Percentage;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CosmicBaseballLv3Test {

    @Disabled("확률 테스트 메서드는 로컬 환경에서 독립적으로 실행합니다.")
    @DisplayName("fever inning 에는 안타 확률이 2배가 됩니다.")
    @Test
    void t1() {
        int hitCount = getHitCount();
        int hitCountInFeverInning = getHitCountInFeverInning();
        assertThat(hitCountInFeverInning).isCloseTo(hitCount*2, Percentage.withPercentage(1));
    }

    @DisplayName("3루가 없어집니다.")
    @Test
    void t2() {
        ScoreBoard scoreBoard = new ScoreBoard(List.of(1,2));
        scoreBoard.startFeverInning();

        scoreBoard.adjust(HIT);

        assertThat(scoreBoard.getScore()).isEqualTo(1);
    }

    private int getHitCount() {
        int count = 0;
        BattingResult result = RandomBattingResultGenerator.get(20, HIT);
        if (result == HIT) {
            count++;
        }
        return count;
    }

    private int getHitCountInFeverInning() {
        int count = 0;
        BattingResult resultOnFever = RandomBattingResultGenerator.getResultOnFever();
        if (resultOnFever == HIT) {
            count++;
        }
        return count;
    }
}
