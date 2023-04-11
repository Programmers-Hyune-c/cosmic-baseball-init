package com.hyunec.cosmicbaseballinit.controller;

import com.hyunec.cosmicbaseballinit.model.BattingResult;
import com.hyunec.cosmicbaseballinit.service.BattingServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class BattingController {

    private final BattingServiceImpl battingServiceImpl;

    @GetMapping("/batting")
    public BattingResult getBattingResult() {
        return battingServiceImpl.batting();
    }
}
