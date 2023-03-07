package com.hyunec.cosmicbaseballinit.domain;

import java.util.Map;

public class Strike implements BattingResult {

    private static final String NAME = "Strike";

    @Override
    public void call(Map<String, Integer> resultCount) {
        resultCount.compute("strike", (k, v) -> v + 1);
    }


    @Override
    public String getName() {
        return NAME;
    }
}
