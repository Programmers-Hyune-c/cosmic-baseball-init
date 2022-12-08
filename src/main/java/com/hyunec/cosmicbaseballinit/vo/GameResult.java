package com.hyunec.cosmicbaseballinit.vo;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum GameResult {
    WIN("WIN"),
    LOSE("LOSE"),
    DRAW("DRAW");

    final String  resultName;

}
