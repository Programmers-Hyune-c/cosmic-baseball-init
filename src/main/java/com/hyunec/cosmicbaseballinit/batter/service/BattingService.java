package com.hyunec.cosmicbaseballinit.batter.service;

import com.hyunec.cosmicbaseballinit.batter.domain.Batting;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class BattingService {

    static final BattingStrategy strategy = new RandomBattingStrategy();

    public Batting getRandomBattingResult() {
        Batting results = Batting.of(strategy);
        log.info("### Batting result={}", results);
        return results;
    }
}
