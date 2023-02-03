package com.hyunec.cosmicbaseballinit.acceptancetest;

import static org.assertj.core.api.Assertions.*;

import com.hyunec.cosmicbaseballinit.domain.baseball.model.Batting;
import com.hyunec.cosmicbaseballinit.domain.baseball.model.BattingResult;
import com.hyunec.cosmicbaseballinit.domain.baseball.model.PlateAppearances;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class NormalBaseballLv1Test {
    @DisplayName("strike, ball, hit 는 같은 확률 입니다.")
    @Test
    void t1() {
        throw new RuntimeException("Not yet implemented");
    }

    @DisplayName("3B 타석에서 타격 결과가 ball 이면 타석 결과는 four_ball 됩니다.")
    @Test
    void t2() {
        //given
        PlateAppearances plateAppearances = new PlateAppearances();
        plateAppearances.batting(Batting.BALL);
        plateAppearances.batting(Batting.BALL);
        plateAppearances.batting(Batting.BALL);

        //when
        plateAppearances.batting(Batting.BALL);

        //then
        assertThat(plateAppearances.result()).isEqualTo(BattingResult.FOUR_BALL);
    }

    @DisplayName("2S 타석에서 타격 결과가 strike 이면 타석 결과는 out 됩니다.")
    @Test
    void t3() {
        //given
        PlateAppearances plateAppearances = new PlateAppearances();
        plateAppearances.batting(Batting.STRIKE);
        plateAppearances.batting(Batting.STRIKE);

        //when
        plateAppearances.batting(Batting.STRIKE);

        //then
        assertThat(plateAppearances.result()).isEqualTo(BattingResult.OUT);
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
