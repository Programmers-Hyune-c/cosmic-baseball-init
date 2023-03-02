package com.hyunec.cosmicbaseballinit.acceptancetest;

ㅑimport static com.hyunec.cosmicbaseballinit.BattingResult.*;
import static org.assertj.core.api.AssertionsForClassTypes.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.hyunec.cosmicbaseballinit.Batter;
import com.hyunec.cosmicbaseballinit.BattingResult;

class NormalBaseballLv1Test {
    @DisplayName("타격 결과는 모두 같은 확률을 가집니다.")
    @Test
    void t1() {
        Batter batter = new Batter();
        BattingResult result = batter.hit();
        assertThat(result).isIn(STRIKE, BALL, HIT);
    }

    @DisplayName("타격 결과는 strike, ball, hit 입니다.")
    @Test
    void t2() {
        throw new RuntimeException("Not yet implemented");
    }
}
