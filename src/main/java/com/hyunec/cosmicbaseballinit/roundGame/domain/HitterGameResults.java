package com.hyunec.cosmicbaseballinit.roundGame.domain;

import com.hyunec.cosmicbaseballinit.roundGame.domain.vo.HitterGameResult;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.List;

@RequiredArgsConstructor
@ToString
@Getter
public class HitterGameResults {
    private final List<HitterGameResult> hitterGameResults;

    public List<HitterGameResult> get() {
        return hitterGameResults;
    }
}
