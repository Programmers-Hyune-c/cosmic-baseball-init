package com.hyunec.cosmicbaseballinit.roundGame.persistence.dto;

import com.hyunec.cosmicbaseballinit.roundGame.domain.vo.HitterGameResult;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor
@Getter
@ToString
public class PastHitterGameResultDto {
    private final HitterGameResult hitterGameResults;

    public HitterGameResult get() {
        return hitterGameResults;
    }
}
