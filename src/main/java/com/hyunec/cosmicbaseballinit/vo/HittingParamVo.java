package com.hyunec.cosmicbaseballinit.vo;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Getter
@RequiredArgsConstructor
public class HittingParamVo {
    private final Double pitchResultRandomDouble;
    private final Double hitterResultRandomDouble;
}
