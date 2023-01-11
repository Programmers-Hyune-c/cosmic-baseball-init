package com.hyunec.cosmicbaseballinit.roundGame.persistence.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.LinkedList;
import java.util.Queue;

@RequiredArgsConstructor
@Getter
@Builder
public class BaseDto {
    private final Queue<Integer> bases;
}
