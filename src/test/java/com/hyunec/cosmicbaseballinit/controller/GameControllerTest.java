package com.hyunec.cosmicbaseballinit.controller;

import com.hyunec.cosmicbaseballinit.service.lv1.Lv1GameService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class GameControllerTest {


    GameController gameController = new GameController(new Lv1GameService());

    @Test
    @DisplayName("게임 시작 메서드가 게임의 결과값을 반환하는지 테스트")
    void gameStartTest(){
        String result = gameController.gameStart();

        assertThat(result).isEqualTo("DRAW");
    }
}
