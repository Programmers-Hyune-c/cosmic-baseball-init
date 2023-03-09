package com.hyunec.cosmicbaseballinit.service;

import com.hyunec.cosmicbaseballinit.domain.Ball;
import com.hyunec.cosmicbaseballinit.domain.BattingResult;
import com.hyunec.cosmicbaseballinit.domain.DoubleBall;
import com.hyunec.cosmicbaseballinit.domain.DoubleStrike;
import com.hyunec.cosmicbaseballinit.domain.Hit;
import com.hyunec.cosmicbaseballinit.domain.Strike;
import java.util.ArrayList;
import java.util.List;

public class BattingResultGenerator {

    private BattingResultGenerator() {
    }

    public static List<BattingResult> of(
                                            Strike strike,
                                            Ball ball,
                                            Hit hit,
                                            DoubleStrike doubleStrike,
                                            DoubleBall doubleBall
    ) {
        return new ArrayList<>(List.of(strike, ball, hit, doubleStrike, doubleBall));
    }

}

