package com.hyunec.cosmicbaseballinit.dto;

import com.hyunec.cosmicbaseballinit.contoller.InningType;
import com.hyunec.cosmicbaseballinit.domain.BattingResult;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

@Data
public class GamePatchDto {

    private InningType inningType;
    private BattingResult targetResult;
    @Range(min = 0, max = 100, message = "확률은 0부터 100이하여야 합니다.")
    private int percentage;
    @Range(min = 0, max = 2, message = "3 OUT이 되어 게임이 종료됩니다.")
    private int outCount;
}
