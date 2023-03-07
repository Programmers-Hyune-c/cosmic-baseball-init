package com.hyunec.cosmicbaseballinit.dto;

import java.util.Map;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ResponseDto {

    private final String result;
    private final Map<String, Integer> resultCount;
}
