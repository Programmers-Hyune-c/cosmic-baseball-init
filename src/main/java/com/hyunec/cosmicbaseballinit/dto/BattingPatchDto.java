package com.hyunec.cosmicbaseballinit.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import lombok.Data;

@Data
public class BattingPatchDto {

    @Max(100)
    @Min(1)
    private int percentage;
    private String targetResult;
    @Min(0)
    @Max(3)
    private int outCount;
}
