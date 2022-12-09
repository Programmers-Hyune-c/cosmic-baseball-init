package com.hyunec.cosmicbaseballinit.hitter.ui;

import com.hyunec.cosmicbaseballinit.hitter.application.BattingService;
import com.hyunec.cosmicbaseballinit.hitter.domain.Batting;
import com.hyunec.cosmicbaseballinit.hitter.domain.RandomGenerate;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class BattingController {

    private final BattingService battingService;
    private final RandomGenerate randomGenerate;

    @GetMapping("/game/batting")
    public String batting() {
        Batting batting = randomGenerate.generate();
        return battingService.swing(batting);
    }
}
