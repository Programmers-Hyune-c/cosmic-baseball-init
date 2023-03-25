package com.hyunec.cosmicbaseballinit.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ExceptionType {
    NO_NEW_BATTING_ON_GOING("타석이 진행 중 일때, 새로운 타석은 불가능합니다."),
    END_INNING("이닝이 종료되어 게임이 종료됩니다.");

    private final String message;
}
