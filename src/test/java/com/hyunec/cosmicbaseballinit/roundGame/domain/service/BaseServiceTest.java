package com.hyunec.cosmicbaseballinit.roundGame.domain.service;

import com.hyunec.cosmicbaseballinit.roundGame.domain.repository.BaseRepository;
import com.hyunec.cosmicbaseballinit.roundGame.persistence.dto.BaseDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Queue;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BaseServiceTest {

    @Autowired
    BaseService baseService;

    @Autowired
    BaseRepository baseRepository;

    @Test
    void advancingBaseTest_1루_진루() {

        // when
        baseService.advancingBase();

        // then
        BaseDto baseDto = baseRepository.getBases();
        Queue<Integer> bases = baseDto.getBases();
        assertThat(bases.size()).isEqualTo(1);
        assertThat(bases.poll()).isEqualTo(1);
    }

    @Test
    @Disabled
    void plusScore() {
    }
}
