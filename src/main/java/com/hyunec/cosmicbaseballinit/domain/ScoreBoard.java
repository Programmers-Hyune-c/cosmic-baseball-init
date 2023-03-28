package com.hyunec.cosmicbaseballinit.domain;

import static com.hyunec.cosmicbaseballinit.domain.BatterStatus.GO_TO_BASE;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
public class ScoreBoard {

    @Setter
    private Long id;
    private int score;
    private static final int COUNT_OF_BASES = 4;
    private final List<Integer> onBaseList = new ArrayList<>();
    private final AtBatResult atBatResult = new AtBatResult();
    private boolean feverInning;

    public ScoreBoard(List<Integer> baseNumbers) {
        onBaseList.addAll(baseNumbers);
    }

    public void adjust(BattingResult result) {
        atBatResult.adjustAccordingToBattingResult(result);
        if (atBatResult.getBatterStatus() == GO_TO_BASE) {
            adjustBaseAndScore();
        }
    }

    private void adjustBaseAndScore() {
        onBaseList.add(onBaseList.size() + 1);
        if (isRunInFeverInning()) {
            doScoringProcess();
            return;
        }

        if (isRun()) {
            doScoringProcess();
        }
    }

    private boolean isRunInFeverInning() {
        return this.feverInning && onBaseList.get(onBaseList.size() - 1) == 3;
    }

    private void doScoringProcess() {
        score++;
        onBaseList.clear();
    }

    private boolean isRun() {
        return COUNT_OF_BASES == onBaseList.get(onBaseList.size() - 1);
    }

    public void startFeverInning() {
        this.feverInning = true;
    }
}
