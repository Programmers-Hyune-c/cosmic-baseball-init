package com.hyunec.cosmicbaseballinit.domain;

import java.util.Map;

public class DoubleStrike implements BattingResult {

    @Override
    public void call(Map<String, Integer> resultCount) {
        resultCount.compute("strike", (k, v) -> v + 2);
    }

    @Override
    public String getName() {
        return "Double Strike";
    }
}
