package com.hyunec.cosmicbaseballinit.web;

import com.hyunec.cosmicbaseballinit.domain.baseball.model.Batting;
import com.hyunec.cosmicbaseballinit.domain.baseball.model.BattingResult;
import com.hyunec.cosmicbaseballinit.domain.baseball.model.PlateAppearances;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
public class GameController {

    private final PlateAppearances plateAppearances;

    @GetMapping("/game/batting")
    public BattingResult batting() {
        plateAppearances.batting(Batting.generate());
        log.info("### plateAppearances.result()={}", plateAppearances.result());
        return plateAppearances.result();
    }
}
