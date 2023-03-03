package com.hyunec.cosmicbaseballinit.service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Component;

@Component
public class BallCountService {

    public static final Map<String, Integer> COUNT_STORE =
        new ConcurrentHashMap<>(Map.of("strike", 0, "ball", 0));

    public void addBallCount(BattingResult result) {

        switch (result.name()) {
            case "STRIKE":
                COUNT_STORE.compute("strike", (k, v) -> (v + 1));
                break;
            case "BALL":
                COUNT_STORE.compute("ball", (k, v) -> (v + 1));
                break;
            case "DOUBLE_STRIKE":
                COUNT_STORE.compute("strike", (k, v) -> (v + 2));
                break;
            default:
                resetStore();
        }
    }

    private void resetStore() {
        COUNT_STORE.keySet().forEach(key -> COUNT_STORE.put(key, 0));
    }
}
