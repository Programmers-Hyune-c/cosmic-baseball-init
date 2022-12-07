package com.hyunec.cosmicbaseballinit.controller;

import com.hyunec.cosmicbaseballinit.service.OneWeekGameService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class GameControllerTest {


    GameController gameController = new GameController(new OneWeekGameService());

    @Test
    void gameStartTest(){
        String result = gameController.gameStart();

        assertThat(result).isEqualTo("DRAW");
    }
}