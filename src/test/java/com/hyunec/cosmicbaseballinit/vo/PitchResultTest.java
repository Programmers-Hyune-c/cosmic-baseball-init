package com.hyunec.cosmicbaseballinit.vo;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class PitchResultTest {

    @Test
    void getResultName() {
        String name = PitchResult.Hit.getResultName();

        assertThat(name).isEqualTo("Hit");
    }
}