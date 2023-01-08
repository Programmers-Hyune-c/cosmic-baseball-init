package com.hyunec.cosmicbaseballinit.roundGame.domain;

import com.hyunec.cosmicbaseballinit.roundGame.domain.vo.PastHitterGameResult;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.List;

@RequiredArgsConstructor
@ToString
public class PastHitterGameResultList {
    private final List<PastHitterGameResult> pastList;

    public List<PastHitterGameResult> get() {
        return pastList;
    }
}
