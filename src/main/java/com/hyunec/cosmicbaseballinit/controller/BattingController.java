package com.hyunec.cosmicbaseballinit.controller;

import com.hyunec.cosmicbaseballinit.model.BattingResult;
import com.hyunec.cosmicbaseballinit.service.BattingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class BattingController {

    private final BattingService battingService;

    @GetMapping("/batting")
    public ResponseEntity<BattingResult> getBattingResult() {
        BattingResult result = battingService.batting();

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
