package com.hyunec.cosmicbaseballinit.domain;

import java.util.Map;

public class Ball implements BattingResult {

    @Override
    public void call(Map<String, Integer> resultCount) {
        resultCount.compute("ball", (k, v) -> v + 1);
    }

    @Override
    public String getName() {
        return "Ball";
    }
}
