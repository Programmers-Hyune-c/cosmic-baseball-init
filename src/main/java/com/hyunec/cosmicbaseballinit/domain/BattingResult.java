package com.hyunec.cosmicbaseballinit.domain;

import java.util.Map;

public interface BattingResult {

    String getName();

    void call(Map<String, Integer> resultCount);
}
