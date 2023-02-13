package com.hyunec.cosmicbaseballinit.domain.baseball.service;

import com.hyunec.cosmicbaseballinit.domain.baseball.model.Batting;
import com.hyunec.cosmicbaseballinit.domain.baseball.model.BattingResult;
import com.hyunec.cosmicbaseballinit.domain.baseball.repository.PlateAppearances;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class GameService {

    private final PlateAppearances plateAppearances;

    public BattingResult batting() {
        plateAppearances.batting(Batting.generate());
        log.info("### plateAppearances.result()={}", plateAppearances.result());
        return plateAppearances.result();
    }
}
