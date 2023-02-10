package com.hyunec.cosmicbaseballinit.acceptancetest;

import com.hyunec.cosmicbaseballinit.domain.baseball.model.service.BaseballService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CosmicBaseballLv1Test {

    @InjectMocks
    private BaseballService baseballService;

    @DisplayName("strike 의 20% 는 bullseye_strike 입니다.")
    @Test
    void t1() {
        //given
//        given(baseballService.batting()).willReturn(Batting.BULLSEYE_STRIKE);

        //when
//        baseballService.batting();

        //then
//        assertThat(baseballService.getBattingResult()).isEqualTo(BattingResult.OUT);
    }

    @DisplayName("ball 의 20% 는 bullseye_ball 입니다.")
    @Test
    void t2() {
        throw new RuntimeException("Not yet implemented");
    }

    @DisplayName("hit 의 20% 는 homerun 입니다.")
    @Test
    void t3() {
        throw new RuntimeException("Not yet implemented");
    }

    @DisplayName("타격 결과가 bullseye_strike 이면 타석 결과는 out 됩니다.")
    @Test
    void t4() {
        throw new RuntimeException("Not yet implemented");
    }

    @DisplayName("타격 결과가 bullseye_ball 이면 타석 결과는 hit 됩니다.")
    @Test
    void t5() {
        throw new RuntimeException("Not yet implemented");
    }
}
