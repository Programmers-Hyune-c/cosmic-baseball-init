package com.hyunec.cosmicbaseballinit.domain;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ScoreBoard {

    private static final int COUNT_OF_BASES = 4;
    private final List<Integer> onBaseList = new ArrayList<>();
    private int score;

    public ScoreBoard(List<Integer> baseNumbers ) {
        onBaseList.addAll(baseNumbers);
    }

    public void adjustBaseAndScore() {
        onBaseList.add(onBaseList.size() + 1);
        if (isRun()) {
            doScoringProcess();
        }
    }

    private void doScoringProcess() {
        score++;
        onBaseList.clear();
    }

    private boolean isRun() {
        return COUNT_OF_BASES == onBaseList.get(onBaseList.size() - 1);
    }
}
