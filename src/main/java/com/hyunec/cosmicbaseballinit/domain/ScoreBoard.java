package com.hyunec.cosmicbaseballinit.domain;

import static com.hyunec.cosmicbaseballinit.domain.BatterStatus.GO_TO_BASE;

import lombok.Getter;

@Getter
public abstract class ScoreBoard implements Score {

    private final AtBatResult atBatResult = new AtBatResult();
    private final BaseList baseList;

    protected ScoreBoard(BaseList baseList) {
        this.baseList = baseList;
    }

    public void adjust(BattingResult result) {
        atBatResult.adjustAccordingToBattingResult(result);
        if (atBatResult.getBatterStatus() == GO_TO_BASE) {
            adjustBaseAndScore();
        }
    }

    public abstract void setId(Long id);
}
