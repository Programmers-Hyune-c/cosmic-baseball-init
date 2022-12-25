package com.hyunec.cosmicbaseballinit.vo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class HitterResultTest {

    @Test
    @DisplayName("Strike 이고, HitterScore가 Strike 2 일 때, STRIKE_OUT 반환")
    void judgeHitterResultByPitchResultNull() throws Exception{
        // when
        HitterResult hitterResult = HitterResult.judgeHitterResultByPitchResult(
                PitchResult.STRIKE, 3);

        //then
        assertThat(hitterResult).isEqualTo(HitterResult.STRIKE_OUT);

    }

    @Test
    @DisplayName("Strike 이고, 랜덤 확률이 0.2 이하 일 때, BULLSEYE_STRIKE 반환")
    void judgeInCaseOfSpecialTest() throws Exception{

        // when
        HitterResult hitterResult = HitterResult.judgeInCaseOfSpecial(
                PitchResult.STRIKE, 0.1);

        // then
        assertThat(hitterResult).isEqualTo(HitterResult.BULLSEYE_STRIKE);
    }

    @Test
    @DisplayName("Strike 이고, 랜덤 확률이 0.2 이상 일 때, null 반환")
    void judgeInCaseOfSpecialNullTest() throws Exception{

        // when
        HitterResult hitterResult = HitterResult.judgeInCaseOfSpecial(
                PitchResult.STRIKE, 0.3);

        // then
        assertThat(hitterResult).isEqualTo(null);
    }
}
