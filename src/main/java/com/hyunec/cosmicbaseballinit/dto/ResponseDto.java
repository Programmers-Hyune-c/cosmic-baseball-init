package com.hyunec.cosmicbaseballinit.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.hyunec.cosmicbaseballinit.domain.BattingResultCount;
import lombok.Builder;
import lombok.Data;

@Data
@JsonInclude(Include.NON_NULL)
public class ResponseDto {

    private final String result;
    private final BattingResultCount resultCount;

    @Builder
    private ResponseDto(String result, BattingResultCount resultCount) {
        this.result = result;
        this.resultCount = resultCount;
    }

    public static ResponseDto of(BattingResultCount battingResultCount) {
        return ResponseDto.builder().resultCount(battingResultCount).build();
    }

    public static ResponseDto of(String result, BattingResultCount battingResultCount) {
        return ResponseDto.builder().result(result).resultCount(battingResultCount).build();
    }
}

