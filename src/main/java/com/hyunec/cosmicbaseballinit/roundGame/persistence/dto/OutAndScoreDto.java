package com.hyunec.cosmicbaseballinit.roundGame.persistence.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Builder
@RequiredArgsConstructor
public class OutAndScoreDto {
    private final Integer outCount;
    private final Integer scoreCount;
}
