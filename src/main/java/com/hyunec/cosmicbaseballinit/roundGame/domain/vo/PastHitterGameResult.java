package com.hyunec.cosmicbaseballinit.roundGame.domain.vo;

import com.hyunec.cosmicbaseballinit.roundGame.persistence.dto.PastHitterGameResultDto;
import com.hyunec.cosmicbaseballinit.vo.hitterGame.HitterResult;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.List;

@RequiredArgsConstructor
@Getter
@ToString
public class PastHitterGameResult {
    /**
     * ex)
     * pitchResultAndCountVo - {strike:3, ball:2}
     * hitterResult - STRIKE_OUT
     */
    private final PitchResultAndCountVo pitchResultAndCountVo;
    private final HitterResult hitterResult;
}
