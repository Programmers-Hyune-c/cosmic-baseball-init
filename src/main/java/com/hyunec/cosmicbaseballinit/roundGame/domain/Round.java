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

    public Round out() {
        return Round.builder()
                .outCount(outCount + 1)
                .scoreCount(scoreCount)
                .build();
    }

    public Round score(Integer score) {
        return Round.builder()
                .outCount(outCount)
                .scoreCount(scoreCount + score)
                .build();
    }


}
