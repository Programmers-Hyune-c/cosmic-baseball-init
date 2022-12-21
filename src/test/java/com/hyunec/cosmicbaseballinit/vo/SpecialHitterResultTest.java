package com.hyunec.cosmicbaseballinit.vo;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class SpecialHitterResultTest {

    @Test
    void bullseyeStrikeTest() throws Exception{
        // given
        PitchResult pitchResult = PitchResult.STRIKE;

        // when
        SpecialHitterResult specialHitterResult = SpecialHitterResult
                .judgeSpecialHitterResultByPitchResult(pitchResult, 0.19);

        // then
        assertThat(specialHitterResult).isEqualTo(SpecialHitterResult.BULLSEYE_STRIKE);
    }

    @Test
    void outOfProbabilityTest() throws Exception{
        // given
        PitchResult pitchResult = PitchResult.STRIKE;

        // when
        SpecialHitterResult specialHitterResult = SpecialHitterResult
                .judgeSpecialHitterResultByPitchResult(pitchResult, 0.21);

        // then
        assertThat(specialHitterResult).isEqualTo(null);
    }

}
