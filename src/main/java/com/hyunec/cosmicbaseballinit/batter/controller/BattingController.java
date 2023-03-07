package com.hyunec.cosmicbaseballinit.batter.controller;

import com.hyunec.cosmicbaseballinit.batter.domain.Batting;
import com.hyunec.cosmicbaseballinit.batter.service.BattingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class BattingController {

    @GetMapping("/batting")
    public Batting getBattingResult() {
        return BattingService.getRandomBattingResult();
    }
}
