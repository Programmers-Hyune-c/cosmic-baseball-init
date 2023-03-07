package com.hyunec.cosmicbaseballinit.domain;

import java.util.Map;

public class Hit implements BattingResult {

    private static final String NAME = "Hit";

    @Override
    public void call(Map<String, Integer> resultCount) {
        resultCount.keySet().forEach(k -> resultCount.put(k, 0));
    }


    @Override
    public String getName() {
        return NAME;
    }
}
