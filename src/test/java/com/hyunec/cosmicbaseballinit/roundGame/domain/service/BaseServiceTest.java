package com.hyunec.cosmicbaseballinit.roundGame.domain.service;

import com.hyunec.cosmicbaseballinit.roundGame.domain.Base;
import com.hyunec.cosmicbaseballinit.roundGame.domain.repository.BaseRepository;
import com.hyunec.cosmicbaseballinit.roundGame.domain.repository.RoundRepository;
import com.hyunec.cosmicbaseballinit.roundGame.persistence.dto.BaseDto;
import com.hyunec.cosmicbaseballinit.roundGame.persistence.dto.OutAndScoreDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
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
    @Autowired
    RoundRepository roundRepository;

    @AfterEach
    void initData() {
        baseRepository.initBases();
        roundRepository.initRoundScore();
    }

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
    @Disabled // TODO: 삭제 예정
    void plusScoreTest_1점_득점() {
        // when
        baseService.plusScore(1);

        // then
        OutAndScoreDto roundScore = roundRepository.getRoundScore();
        assertThat(roundScore.getScoreCount()).isEqualTo(1);
    }

    @Test
    @Disabled //TODO: 삭제 예정
    void advancingBaseTest_홈베이스_진루후_1점득점() {
        // given
        Integer repeatCount = Base.getTotalBaseCount() + 1;

        // when
        for (int i = 0; i < repeatCount; i++){
            baseService.advancingBase();
        }

        // then
        BaseDto baseDto = baseRepository.getBases();
        Queue<Integer> bases = baseDto.getBases();

        OutAndScoreDto roundScore = roundRepository.getRoundScore();

        // 1, 2, 3루 진루
        assertThat(bases.size()).isEqualTo(3);
        // 1점 득점
        assertThat(roundScore.getScoreCount()).isEqualTo(1);
    }
}
