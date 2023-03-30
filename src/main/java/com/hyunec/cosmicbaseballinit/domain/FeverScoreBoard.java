package com.hyunec.cosmicbaseballinit.domain;

import lombok.Getter;


@Getter
public class FeverScoreBoard extends ScoreBoard {

    private Long id;
    private int score;

    public FeverScoreBoard(BaseList baseList) {
        super(baseList);
    }

    @Override
    public void adjustBaseAndScore() {
        BaseList baseList = super.getBaseList();
        baseList.addBase();
        if (baseList.isFeverRun()) {
            doScoringProcess();
            baseList.modifyBaseList();
        }
    }

    @Override
    public void doScoringProcess() {
        score++;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }
}
