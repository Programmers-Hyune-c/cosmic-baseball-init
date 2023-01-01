package com.hyunec.cosmicbaseballinit.config.vo;

import com.hyunec.cosmicbaseballinit.vo.HitterResult;
import com.hyunec.cosmicbaseballinit.vo.PitchResult;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

class HitterResultTest {

    @Test
    @DisplayName("Strike 이고, HitterScore가 Strike 2 이고, 랸덤 확률이 0.2 이상일 때 STRIKE_OUT 반환")
    void judgeHitterResultByPitchResultNull() throws Exception{
        // when
        Optional<HitterResult> hitterResult = HitterResult.judgeHitterResultByPitchResult(
                PitchResult.STRIKE, 3, 0.3);

        //then
        assertThat(hitterResult.get()).isEqualTo(HitterResult.STRIKE_OUT);

    }

    @Test
    @DisplayName("Strike 이고, 랜덤 확률이 0.2 이하 일 때, BULLSEYE_STRIKE 반환")
    void judgeInCaseOfSpecialTest() throws Exception{

        // when
        Optional<HitterResult> hitterResult = HitterResult.judgeHitterResultByPitchResult(
                PitchResult.STRIKE, 2, 0.19);

        // then
        assertThat(hitterResult.get()).isEqualTo(HitterResult.BULLSEYE_STRIKE);
    }

    @Test
    @DisplayName("Hit 이고, 랜덤 확률이 0.2 미만 일 때, HomeRun 반환")
    void judgeInCaseOfHomerunTest() throws Exception{

        // when
        Optional<HitterResult> hitterResult = HitterResult.judgeHitterResultByPitchResult(
                PitchResult.HIT, 0, 0.19);

        // then
        assertThat(hitterResult.get()).isEqualTo(HitterResult.HOMERUN);
    }

    @Test
    @DisplayName("Strike 이고, 2스트라이크 미만이고 ,랜덤 확률이 0.2 이상 일 때, null 반환")
    void judgeInCaseOfSpecialNullTest() throws Exception{

        // when
        Optional<HitterResult> hitterResult = HitterResult.judgeHitterResultByPitchResult(
                PitchResult.STRIKE, 1, 0.3);
        // then
        assertThat(hitterResult.isPresent()).isFalse();
    }
}
