package com.hyunec.cosmicbaseballinit.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import lombok.Data;

@Data
public class ValidationDto {
    @Min(0)
    @Max(value = 2, message = "3 out이 되어 게임이 종료됩니다.")
    private int outCount;

}
