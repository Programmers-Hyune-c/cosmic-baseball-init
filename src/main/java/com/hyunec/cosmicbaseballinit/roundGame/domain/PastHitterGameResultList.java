package com.hyunec.cosmicbaseballinit.roundGame.domain;

import com.hyunec.cosmicbaseballinit.roundGame.domain.vo.PastHitterGameResult;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class PastHitterGameResultList {
    private final List<PastHitterGameResult> pastList;

    public List<PastHitterGameResult> get() {
        return pastList;
    }
}
