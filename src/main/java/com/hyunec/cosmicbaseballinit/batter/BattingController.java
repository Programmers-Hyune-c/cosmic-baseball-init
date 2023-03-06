package com.hyunec.cosmicbaseballinit.batter;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
public class BattingController {

    private final BattingResultService battingResultService;

    @GetMapping("/batting")
    public Batting getBattingResult() {
        return battingResultService.getBattingResult();
    }
}
