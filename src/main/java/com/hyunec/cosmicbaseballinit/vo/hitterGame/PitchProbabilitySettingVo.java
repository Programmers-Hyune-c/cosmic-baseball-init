package com.hyunec.cosmicbaseballinit.vo.hitterGame;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@Builder
public class PitchProbabilitySettingVo {
    private final Double strikeProbabiltiy;
    private final Double ballProbabiltiy;
    private final Double hitProbabiltiy;
}
