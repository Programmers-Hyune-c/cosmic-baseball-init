package com.hyunec.cosmicbaseballinit.batter.service;

import com.hyunec.cosmicbaseballinit.batter.domain.Batting;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Service
public class BattingService {

    private static final BattingFactory factory = new RandomBattingFactory();

    public static Batting getRandomBattingResult() {
        Batting results = Batting.of(factory);
        log.info("### Batting result={}", results);
        return results;
    }
}
