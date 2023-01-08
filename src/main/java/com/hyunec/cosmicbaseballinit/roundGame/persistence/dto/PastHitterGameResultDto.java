package com.hyunec.cosmicbaseballinit.roundGame.persistence.dto;

import com.hyunec.cosmicbaseballinit.roundGame.domain.vo.PastHitterGameResult;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor
@Getter
@ToString
public class PastHitterGameResultDto {
    private final PastHitterGameResult pastHitterGameResults;

    public PastHitterGameResult get() {
        return pastHitterGameResults;
    }
}
