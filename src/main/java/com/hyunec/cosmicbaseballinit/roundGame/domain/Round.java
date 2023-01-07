package com.hyunec.cosmicbaseballinit.roundGame.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
@Builder
public class Round {

    private final Integer outCount;
    private final Integer scoreCount;
    private final Base base;

    public Round plusOut() {
        return Round.builder()
                .outCount(outCount + 1)
                .scoreCount(scoreCount + 1)
                .base(base)
                .build();
    }

    public Round plusScore() {
        return Round.builder()
                .outCount(outCount)
                .scoreCount(scoreCount + 1)
                .base(base)
                .build();
    }


}
