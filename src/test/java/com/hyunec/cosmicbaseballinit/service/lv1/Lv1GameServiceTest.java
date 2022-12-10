package com.hyunec.cosmicbaseballinit.service.lv1;

import com.hyunec.cosmicbaseballinit.service.lv1.Lv1GameService;
import com.hyunec.cosmicbaseballinit.vo.GameResult;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class Lv1GameServiceTest {

    Lv1GameService lv1GameService = new Lv1GameService();
    
    @Test
    @DisplayName("게임 진행 함수 테스트")
    public void gameStartTest () throws Exception {
        // given

        // when
        lv1GameService.gameStart();
        
        // then
    }

    @Test
    @DisplayName("게임 승패 반환 메서드 테스트")
    void returnGameResultTest(){
        Integer myTeamScore = 1;
        Integer otherTeamScore = 4;

        GameResult gameResult = lv1GameService.returnGameResult(myTeamScore, otherTeamScore);

        assertThat(gameResult).isEqualTo(GameResult.LOSE);
    }
}
