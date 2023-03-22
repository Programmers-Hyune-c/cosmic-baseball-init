package com.hyunec.cosmicbaseballinit.domain;

import lombok.Getter;

@Getter
public class ScoreBoard {

    private boolean inFirstBase;
    private boolean inSecondBase;
    private boolean inThirdBase;
    private int score;

    private ScoreBoard(boolean inFirstBase, boolean inSecondBase, boolean inThirdBase) {
        this.inFirstBase = inFirstBase;
        this.inSecondBase = inSecondBase;
        this.inThirdBase = inThirdBase;
    }

    public static ScoreBoard of(boolean inFirstBase, boolean inSecondBase, boolean inThirdBase) {
        return new ScoreBoard(inFirstBase, inSecondBase, inThirdBase);
    }

    public void adjustBaseAndScore() {
        if (firstIsFalse()) {
            this.inFirstBase = true;
            return;
        }
        if (secondIsFalse()) {
            this.inSecondBase = true;
            return;
        }
        if (thirdIsFalse()) {
            this.inThirdBase = true;
            return;
        }
        this.score++;
        resetBase();
    }

    private boolean thirdIsFalse() {
        return !this.inThirdBase;
    }

    private boolean secondIsFalse() {
        return !this.inSecondBase;
    }

    private boolean firstIsFalse() {
        return !this.inFirstBase;
    }

    private void resetBase() {
        this.inFirstBase = false;
        this.inSecondBase = false;
        this.inThirdBase = false;
    }
}
