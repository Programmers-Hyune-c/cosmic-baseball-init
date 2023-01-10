package com.hyunec.cosmicbaseballinit.roundGame.domain.vo;

import com.hyunec.cosmicbaseballinit.roundGame.domain.HitterGameResults;
import com.hyunec.cosmicbaseballinit.roundGame.domain.Round;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@RequiredArgsConstructor
@ToString
public class RoundGameResult {
    private final HitterGameResults hitterGameResult;
    private final Round round;
}
