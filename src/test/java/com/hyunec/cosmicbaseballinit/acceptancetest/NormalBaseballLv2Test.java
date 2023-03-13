package com.hyunec.cosmicbaseballinit.acceptancetest;

import static com.hyunec.cosmicbaseballinit.domain.BatterStatus.GO_TO_BASE;
import static com.hyunec.cosmicbaseballinit.domain.BatterStatus.ON_GOING;
import static com.hyunec.cosmicbaseballinit.domain.BatterStatus.OUT;
import static com.hyunec.cosmicbaseballinit.domain.BattingResult.BALL;
import static com.hyunec.cosmicbaseballinit.domain.BattingResult.DOUBLE_BALL;
import static com.hyunec.cosmicbaseballinit.domain.BattingResult.DOUBLE_STRIKE;
import static com.hyunec.cosmicbaseballinit.domain.BattingResult.HIT;
import static com.hyunec.cosmicbaseballinit.domain.BattingResult.STRIKE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

import com.hyunec.cosmicbaseballinit.domain.BatterStatus;
import com.hyunec.cosmicbaseballinit.domain.TotalBattingResult;
import com.hyunec.cosmicbaseballinit.exception.NewBattingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class NormalBaseballLv2Test {

    private TotalBattingResult totalResult;

    @BeforeEach
    void init() {
        totalResult = new TotalBattingResult();
    }

    @DisplayName("타석 상태를 표현할 수 있습니다.")
    @Test
    void t1() {
        assertThat(BatterStatus.values()).contains(OUT, ON_GOING, GO_TO_BASE);
    }

    @DisplayName("0 strike 상태의 타석에서 타격 결과가 strike 이면 타석 결과는 진행 중 입니다.")
    @Test
    void t2() {
        totalResult.addBattingResultCount(STRIKE);
        totalResult.judgeBatterStatus();

        assertThat(totalResult.getBatterStatus()).isEqualTo(ON_GOING);
    }

    @DisplayName("2 strike 상태의 타석에서 타격 결과가 strike 이면 타석 결과는 out 입니다.")
    @Test
    void t3() {
        totalResult.addBattingResultCount(DOUBLE_STRIKE);
        totalResult.addBattingResultCount(STRIKE);
        totalResult.judgeBatterStatus();

        assertThat(totalResult.getBatterStatus()).isEqualTo(OUT);

    }

    @DisplayName("0 strike 상태의 타석에서 타격 결과가 double_strike 이면 타석 결과는 진행 중 입니다.")
    @Test
    void t4() {
        totalResult.addBattingResultCount(DOUBLE_STRIKE);
        totalResult.judgeBatterStatus();

        assertThat(totalResult.getBatterStatus()).isEqualTo(ON_GOING);
    }

    @DisplayName("1 strike 상태의 타석에서 타격 결과가 double_strike 이면 타석 결과는 out 입니다.")
    @Test
    void t5() {
        totalResult.addBattingResultCount(STRIKE);
        totalResult.addBattingResultCount(DOUBLE_STRIKE);
        totalResult.judgeBatterStatus();

        assertThat(totalResult.getBatterStatus()).isEqualTo(OUT);
    }

    @DisplayName("0 ball 상태의 타석에서 타격 결과가 ball 이면 타석 결과는 진행 중 입니다.")
    @Test
    void t6() {
        totalResult.addBattingResultCount(BALL);
        totalResult.judgeBatterStatus();

        assertThat(totalResult.getBatterStatus()).isEqualTo(ON_GOING);
    }

    @DisplayName("3 ball 상태의 타석에서 타격 결과가 ball 이면 타석 결과는 four_ball 으로 진루 입니다.")
    @Test
    void t7() {
        totalResult.addBattingResultCount(BALL);
        totalResult.addBattingResultCount(DOUBLE_BALL);
        totalResult.addBattingResultCount(BALL);
        totalResult.judgeBatterStatus();

        assertThat(totalResult.getBatterStatus()).isEqualTo(GO_TO_BASE);
    }

    @DisplayName("0 ball 상태의 타석에서 타격 결과가 double_ball 이면 타석 결과는 진행 중 입니다.")
    @Test
    void t8() {
        totalResult.addBattingResultCount(DOUBLE_BALL);
        totalResult.judgeBatterStatus();
        assertThat(totalResult.getBatterStatus()).isEqualTo(ON_GOING);
    }

    @DisplayName("2 ball 상태의 타석에서 타격 결과가 double_ball 이면 타석 결과는 four_ball 으로 진루 입니다.")
    @Test
    void t9() {
        totalResult.addBattingResultCount(DOUBLE_BALL);
        totalResult.addBattingResultCount(DOUBLE_BALL);
        totalResult.judgeBatterStatus();

        assertThat(totalResult.getBatterStatus()).isEqualTo(GO_TO_BASE);
    }

    @DisplayName("타석 결과가 hit 이면 타석 결과는 hit 로 진루 입니다.")
    @Test
    void t10() {
        totalResult.addBattingResultCount(HIT);
        totalResult.judgeBatterStatus();

        assertThat(totalResult.getBatterStatus()).isEqualTo(GO_TO_BASE);
    }


    @DisplayName("진행 중인 타석이 있는 상태에서 새로운 타석을 진행할 수 없습니다.")
    @Test
    void t11() {

        assertSoftly(soft -> {
            soft.assertThatThrownBy(() -> {
                    this.newBatting(ON_GOING);
                })
                .isInstanceOf(NewBattingException.class)
                .hasMessageContaining("새로운 타석 안됨");

            soft.assertThat(newBatting(OUT).getStrikeCount()).isEqualTo(0);
            soft.assertThat(newBatting(OUT).getBallCount()).isEqualTo(0);
            soft.assertThat(newBatting(GO_TO_BASE).getStrikeCount()).isEqualTo(0);
            soft.assertThat(newBatting(GO_TO_BASE).getBallCount()).isEqualTo(0);
        });

    }

    private TotalBattingResult newBatting(BatterStatus batterStatus) {
        if (batterStatus == ON_GOING) {
            throw new NewBattingException("새로운 타석 안됨");
        }
        return new TotalBattingResult();
    }

}
