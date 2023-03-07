package com.hyunec.cosmicbaseballinit.service;

import com.hyunec.cosmicbaseballinit.domain.BattingResult;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Service;

@Service
public class ResultCountService {

    private static final Map<String, Integer> RESULT_COUNT =
                        new ConcurrentHashMap<>(Map.of("strike", 0, "ball", 0));

    public void addResultCount(BattingResult battingResult){
        battingResult.call(RESULT_COUNT);
    }

    public Map<String, Integer> getResultCount(){
        return RESULT_COUNT;
    }
}

