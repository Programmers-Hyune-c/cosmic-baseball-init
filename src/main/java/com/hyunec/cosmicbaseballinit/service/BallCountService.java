package com.hyunec.cosmicbaseballinit.service;

import com.hyunec.cosmicbaseballinit.domain.BattingResult;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Service;

@Service
public class BallCountService {

    public static final Map<String, Integer> COUNT_STORE =
                            new ConcurrentHashMap<>(Map.of("STRIKE",0, "BALL",0));

    public void setBallCount(BattingResult result) {
        result.call();
    }

    public static void resetBallCount() {
        COUNT_STORE.keySet().forEach(key -> COUNT_STORE.put(key, 0));
    }
}
