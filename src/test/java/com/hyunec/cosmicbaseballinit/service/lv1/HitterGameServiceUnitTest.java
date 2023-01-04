package com.hyunec.cosmicbaseballinit.service.lv1;

import com.hyunec.cosmicbaseballinit.vo.hitterGame.PitchProbabilitySettingVo;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
public class HitterGameServiceUnitTest {
    @Autowired
    HitterGameService hitterGameService;

    @Test
    @DisplayName("확률이 입력값으로 들어 왔을 때, 올바르게 세팅되는지")
    void setHitGameProbabilityTest() {
        // given
        PitchProbabilitySettingVo input = PitchProbabilitySettingVo
                                            .builder()
                                            .strikeProbabiltiy(0.4)
                                            .ballProbabiltiy(0.4)
                                            .hitProbabiltiy(0.2)
                                            .build();

        // when
        hitterGameService.setHitGameProbability(input);

        // then
        assertThat(hitterGameService.getPitchProbabilities().size()).isEqualTo(3);
    }

    @Test
    @DisplayName("확률의 합이 1이 아닐때, 에러 반환")
    void setHitGameProbabilityTestFail() {
        // given
        PitchProbabilitySettingVo input = PitchProbabilitySettingVo
                .builder()
                .strikeProbabiltiy(0.4)
                .ballProbabiltiy(0.4)
                .hitProbabiltiy(0.1)
                .build();

        // when, then
        assertThatThrownBy(() ->  hitterGameService.setHitGameProbability(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("확률의 합은 1 이어야 합니다.");
       ;
    }

    //TODO: 수정하기
    @Test
    @Disabled
    @DisplayName("SpecialHitterResult 일 때 점수 초기화 true를 반환하는지")
    void isHitterGameEndTestBullseyeBall(){
        // given
        //String specialHitterResult = SpecialHitterResult.BULLSEYE_BALL.name();

        // when
        //Boolean result = lv1HitterGameService.isHitterGameEnd(specialHitterResult);

        // then
        ///assertThat(result).isEqualTo(true);
        hitterGameService.initGameScore();
    }
}
