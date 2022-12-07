package com.hyunec.cosmicbaseballinit.service;

import com.hyunec.cosmicbaseballinit.vo.GameResult;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.*;

class OneWeekGameTest {

    OneWeekGameService oneWeekGameService = new OneWeekGameService();


    @Test
    void gameFinishedTest(){
        Integer myTeamScore = 1;
        Integer otherTeamScore = 4;

        GameResult gameResult = oneWeekGameService.gameFinished(myTeamScore, otherTeamScore);

        assertThat(gameResult).isEqualTo(GameResult.LOSE);

    }
}