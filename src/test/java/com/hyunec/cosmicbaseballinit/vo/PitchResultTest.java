package com.hyunec.cosmicbaseballinit.vo;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class PitchResultTest {

    @Test
    @DisplayName("투구의 결과 enum 클래스의 name 값이 잘 호출 되는지 테스트")
    void getResultName() {
        String name = PitchResult.HIT.getResultName();

        assertThat(name).isEqualTo("HIT");
    }
}