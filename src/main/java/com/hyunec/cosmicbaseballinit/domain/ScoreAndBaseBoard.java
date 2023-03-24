package com.hyunec.cosmicbaseballinit.domain;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;

@Getter
public class ScoreAndBaseBoard {

    private static final int COUNT_OF_BASES = 4;
    private final List<Integer> onBaseList = new ArrayList<>();
    private int score;

    public void adjustBaseAndScore(int baseCountToAdvance) {
        onBaseList.add(baseCountToAdvance);
        if (isRun()) {
            score++;
        }
    }
    private boolean isRun() {
        return COUNT_OF_BASES == onBaseList.get(onBaseList.size() - 1);
    }
}
