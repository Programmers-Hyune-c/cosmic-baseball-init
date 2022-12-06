package com.hyunec.cosmicbaseballinit.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class GameController {

    public GameController() {
    }

    @GetMapping("/gameStart")
    public void gameStart(){

    }
}
