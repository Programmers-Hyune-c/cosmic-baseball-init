package com.hyunec.cosmicbaseballinit.batting;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class BattingController {

    private final BattingResultService battingResultService;

    @GetMapping("/batting")
    public Batting getBattingResult() {
        return battingResultService.getBattingResult();
    }
}
