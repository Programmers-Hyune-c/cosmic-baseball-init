package com.hyunec.cosmicbaseballinit.roundGame.persistence.dto;

import com.hyunec.cosmicbaseballinit.roundGame.domain.vo.PastHitterGameResult;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class PastHitterGameResultDto {
    private final PastHitterGameResult pastHitterGameResults;

    public PastHitterGameResult get() {
        return pastHitterGameResults;
    }
}
