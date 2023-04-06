package com.hyunec.cosmicbaseballinit.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BattingResult {
    STRIKE(0.3),
    BALL(0.3),
    HIT(0.3);

    private final double probability;
}
