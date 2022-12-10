package com.hyunec.cosmicbaseballinit.service.lv1;

import com.hyunec.cosmicbaseballinit.service.lv1.Lv1GameService;
import com.hyunec.cosmicbaseballinit.vo.GameResult;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class Lv1GameServiceTest {

    Lv1GameService lv1GameService = new Lv1GameService();

    @Test
    @DisplayName("게임 끝남 메서드가 팀 스코어에 따라 올바른 결과 값을 반환하는지 테스트")
    void gameFinishedTest(){
        Integer myTeamScore = 1;
        Integer otherTeamScore = 4;

        GameResult gameResult = lv1GameService.gameFinished(myTeamScore, otherTeamScore);

        assertThat(gameResult).isEqualTo(GameResult.LOSE);

    }
}
