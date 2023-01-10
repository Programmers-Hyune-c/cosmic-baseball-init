package com.hyunec.cosmicbaseballinit.vo.hitterGame;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class HittingRequestVo {
    /**
     * pitchResultRandomDouble
     * - 확률 값을 입력 받음
     * - 처음 세팅한 확률 구간에 따라 STRIKE, BALL, OUT 반환
     * hitterResultRandomDouble
     * - 확률 값을 입력 받음
     * - 0.2 보다 작으면 BULLSEYE_STRIKE, BULLSEYE_BALL, HOMERUN 반환
     */
    private final Double pitchResultRandomDouble;
    private final Double hitterResultRandomDouble;
}
