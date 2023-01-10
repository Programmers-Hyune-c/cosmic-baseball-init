package com.hyunec.cosmicbaseballinit.roundGame.domain.service;

import com.hyunec.cosmicbaseballinit.controller.HitterGameInterface;
import com.hyunec.cosmicbaseballinit.roundGame.domain.HitterGameResults;
import com.hyunec.cosmicbaseballinit.roundGame.domain.repository.BaseRepository;
import com.hyunec.cosmicbaseballinit.roundGame.domain.repository.PastHitterGameResultListRepository;
import com.hyunec.cosmicbaseballinit.roundGame.domain.repository.RoundRepository;
import com.hyunec.cosmicbaseballinit.roundGame.persistence.dto.OutAndScoreDto;
import com.hyunec.cosmicbaseballinit.vo.hitterGame.HitterResult;
import com.hyunec.cosmicbaseballinit.vo.hitterGame.HittingRequestVo;
import com.hyunec.cosmicbaseballinit.vo.hitterGame.PitchResult;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Queue;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class RoundGameServiceTest {

    @Autowired
    HitterGameInterface hitterGameInterface;
    @Autowired
    RoundGameService roundGameService;
    @Autowired
    RoundRepository roundRepository;
    @Autowired
    BaseRepository baseRepository;

    @Autowired
    PastHitterGameResultListRepository  pastResultRepository;

    @AfterEach
    void init(){
        roundRepository.initRoundScore();
        baseRepository.initBases();
    }

    @Test
    void judgeByHitterResultTest_HitterResult가_STRIKEOUT_일때_1아웃() {
        // given
        String hitterResult = HitterResult.STRIKE_OUT.name();

        // when
        roundGameService.judgeByHitterResult(hitterResult);

        // then
        OutAndScoreDto roundScore = roundRepository.getRoundScore();
        assertThat(roundScore.getOutCount()).isEqualTo(1);
    }

    @Test
    void judgeByHitterResultTest_HitterResult가_STRIKEOUT_AND_BULSEYE_STRIKE_일때_2아웃() {
        // given
        String hitterResult1 = HitterResult.STRIKE_OUT.name();
        String hitterResult2 = HitterResult.BULLSEYE_STRIKE.name();

        // when
        roundGameService.judgeByHitterResult(hitterResult1);
        roundGameService.judgeByHitterResult(hitterResult2);

        // then
        OutAndScoreDto roundScore = roundRepository.getRoundScore();
        assertThat(roundScore.getOutCount()).isEqualTo(2);
    }

    @Test
    void judgeByHitterResultTest_HitterResult가_FOURBALL_AND_HIT_일때_2루_진루() {
        // given
        String hitterResult1 = HitterResult.FOUR_BALL.name();
        String hitterResult2 = HitterResult.HIT.name();

        // when
        roundGameService.judgeByHitterResult(hitterResult1);
        roundGameService.judgeByHitterResult(hitterResult2);

        // then
        OutAndScoreDto roundScore = roundRepository.getRoundScore();
        assertThat(roundScore.getOutCount()).isEqualTo(0);

        Queue<Integer> bases = baseRepository.getBases().getBases();
        assertThat(bases.size()).isEqualTo(2);
    }

    @Test
    void judgeByHitterResultTest_HitterResult가_STRIKEOUT_AND_HIT_AND_HOMERUN_일때_2점_득점() {
        // given
        String hitterResult1 = HitterResult.STRIKE_OUT.name();
        String hitterResult2 = HitterResult.HIT.name();
        String hitterResult3 = HitterResult.HOMERUN.name();

        // when
        roundGameService.judgeByHitterResult(hitterResult1);
        roundGameService.judgeByHitterResult(hitterResult2);
        roundGameService.judgeByHitterResult(hitterResult3);

        // then
        OutAndScoreDto roundScore = roundRepository.getRoundScore();
        assertThat(roundScore.getOutCount()).isEqualTo(1);
        assertThat(roundScore.getScoreCount()).isEqualTo(2);

        Queue<Integer> bases = baseRepository.getBases().getBases();
        assertThat(bases.size()).isEqualTo(0);
    }

    @Test
    void hit_getScore_Test_STRIKE_OUT_BULLSEYE_STRIKE() throws Exception{
        // 게임 세팅
        hitterGameInterface.gameSetting();

        HittingRequestVo strikeParam = new HittingRequestVo(0.1, 0.4);
        HittingRequestVo bullseyeStrikeParam = new HittingRequestVo(0.1, 0.1);

        roundGameService.hit(strikeParam);
        roundGameService.hit(strikeParam);
        roundGameService.hit(strikeParam);
        roundGameService.hit(strikeParam);
        roundGameService.hit(bullseyeStrikeParam);
        roundGameService.hit(bullseyeStrikeParam);

        HitterGameResults score = roundGameService.getHitterGameResults();
        System.out.println(score);
        assertThat(score.get().get(0).getHitterResult().equals(HitterResult.STRIKE_OUT));
        assertThat(score.get().get(0).getPitchResultAndCountVo().get().get(PitchResult.STRIKE).equals(3));
        assertThat(score.get().get(0).getHitterResult().equals(HitterResult.BULLSEYE_STRIKE));
        assertThat(score.get().get(0).getPitchResultAndCountVo().get().get(PitchResult.STRIKE).equals(2));
    }

}
