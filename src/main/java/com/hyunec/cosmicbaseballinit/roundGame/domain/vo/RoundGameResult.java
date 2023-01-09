package com.hyunec.cosmicbaseballinit.roundGame.domain.vo;

import com.hyunec.cosmicbaseballinit.roundGame.domain.HitterGameResultList;
import com.hyunec.cosmicbaseballinit.roundGame.domain.Round;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@RequiredArgsConstructor
@ToString
public class RoundGameResult {
    private final HitterGameResultList hitterGameResult;
    private final Round round;
}
