package com.hyunec.cosmicbaseballinit.vo;

import lombok.Getter;

@Getter
public enum GameResult {
    WIN("WIN"),
    LOSE("LOSE"),
    DRAW("DRAW");

    String resultName;
    GameResult(String resultName){
        this.resultName = resultName;
    }
}
