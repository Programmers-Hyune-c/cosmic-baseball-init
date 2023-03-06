package com.hyunec.cosmicbaseballinit.batting;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class BattingResultService {

    public Batting getBattingResult() {
        Batting results = Batting.of();
        log.info("### Batting result={}", results);
        return results;
    }
}
