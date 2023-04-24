package com.hyunec.cosmicbaseballinit.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum BatterStatus {
    STAY("진루하지 않음"),
    ADVANCE("진루"),
    OUT("아웃");

    private final String description;
}
