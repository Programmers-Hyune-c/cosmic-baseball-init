package com.hyunec.cosmicbaseballinit.hitter.ui;

import com.hyunec.cosmicbaseballinit.hitter.application.BattingService;
import com.hyunec.cosmicbaseballinit.hitter.domain.Batting;
import com.hyunec.cosmicbaseballinit.hitter.domain.Battings;
import com.hyunec.cosmicbaseballinit.hitter.domain.BattingGenerator;
import com.hyunec.cosmicbaseballinit.hitter.ui.component.PlateAppearance;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class BattingController {

    private final BattingService battingService;
    private final BattingGenerator battingGenerator;
    private final PlateAppearance plateAppearance;

    @GetMapping("/game/batting")
    public String batting() {
        final Batting batting = battingGenerator.generate();
        final Battings battings = battingService.swing(batting);
        final String battingResult = plateAppearance.result(battings);

        battingService.clearBattings(battingResult);

        return battingResult;
    }
}
