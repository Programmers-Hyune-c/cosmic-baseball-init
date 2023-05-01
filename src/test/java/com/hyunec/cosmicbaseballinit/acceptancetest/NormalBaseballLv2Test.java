package com.hyunec.cosmicbaseballinit.acceptancetest;

import com.hyunec.cosmicbaseballinit.model.BatterStatus;
import com.hyunec.cosmicbaseballinit.model.BattingResult;
import com.hyunec.cosmicbaseballinit.model.PlateStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.Stream;

import static com.hyunec.cosmicbaseballinit.model.BattingResult.*;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class NormalBaseballLv2Test {

    @DisplayName("strike 타석 결과와 관련된 테스트를 진행합니다.")
    @TestFactory
    Stream<DynamicTest> normalBaseBallLv2TestStrikeCollection() {

        PlateStatus plateStatus = new PlateStatus();

        return Stream.of(
                DynamicTest.dynamicTest("새로운 타석이 시작되면 타석 상태를 초기화 합니다.", () -> {
                    assertThat(plateStatus.getBallCount()).isZero();
                    assertThat(plateStatus.getStrikeCount()).isZero();
                    assertThat(plateStatus.getHitCount()).isZero();
                    assertThat(plateStatus.getBatterStatus()).isEqualTo(BatterStatus.STAY);
                }),
                DynamicTest.dynamicTest("0 strike 상태의 타석에서 타격 결과가 strike 이면 타석 결과는 진행 중 입니다.", () -> {
                    BatterStatus batterStatus = updateAndGetBatterStatus(plateStatus, STRIKE);
                    assertThat(batterStatus).isEqualTo(BatterStatus.STAY);
                }),
                DynamicTest.dynamicTest("1 strike 상태의 타석에서 타격 결과가 double_strike 이면 타석 결과는 out 입니다.", () -> {
                    BatterStatus batterStatus = updateAndGetBatterStatus(plateStatus, DOUBLE_STRIKE);
                    assertThat(batterStatus).isEqualTo(BatterStatus.OUT);
                })
        );
    }

    @DisplayName("ball, hit 타석 결과와 관련된 테스트를 진행합니다.")
    @TestFactory
    Stream<DynamicTest> normalBaseBallLv2TestBallCollection() {

        PlateStatus plateStatus = new PlateStatus();

        return Stream.of(
                DynamicTest.dynamicTest("0 ball 상태의 타석에서 타격 결과가 ball 이면 타석 결과는 진행 중 입니다.", () -> {
                    BatterStatus batterStatus = updateAndGetBatterStatus(plateStatus, BALL);
                    assertThat(batterStatus).isEqualTo(BatterStatus.STAY);
                }),
                DynamicTest.dynamicTest("1 ball 상태의 타석에서 타격 결과가 double_ball 이면 타석 결과는 three_ball 으로 진행 중 입니다.", () -> {
                    BatterStatus batterStatus = updateAndGetBatterStatus(plateStatus, DOUBLE_BALL);
                    assertThat(batterStatus).isEqualTo(BatterStatus.STAY);
                }),
                DynamicTest.dynamicTest("타석 결과가 hit 이면 기존 상태와 관계없이 hit 으로 진루 합니다.", () -> {
                    BatterStatus batterStatus = updateAndGetBatterStatus(plateStatus, HIT);
                    assertThat(batterStatus).isEqualTo(BatterStatus.ADVANCE);
                })
        );
    }

    public BatterStatus updateAndGetBatterStatus(PlateStatus plateStatus, BattingResult result) {
        plateStatus.updateBatterResult(result);
        return plateStatus.getBatterStatus();
    }

    @DisplayName("진행 중인 타석이 있는 상태에서 새로운 타석을 진행할 수 없습니다.")
    @Test
    void t11() {
        throw new RuntimeException("Not yet implemented");
    }
}
