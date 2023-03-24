package com.hyunec.cosmicbaseballinit.domain;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ScoreAndBaseBoard {

    private static final int COUNT_OF_BASES = 4;
    private final List<Integer> onBaseList = new ArrayList<>();
    private int score;
    private int baseNumber;

    public ScoreAndBaseBoard(int baseNumber) {
        onBaseList.add(baseNumber);
        this.baseNumber = baseNumber;
    }

    public void adjustBaseAndScore() {
        onBaseList.add(++baseNumber);
        if (isRun()) {
            doScoringProcess();
        }
    }

    private void doScoringProcess() {
        score++;
        onBaseList.clear();
        baseNumber = 0;
    }

    private boolean isRun() {
        return COUNT_OF_BASES == onBaseList.get(onBaseList.size() - 1);
    }
}
