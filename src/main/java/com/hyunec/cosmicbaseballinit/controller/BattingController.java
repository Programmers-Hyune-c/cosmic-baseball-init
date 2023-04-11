package com.hyunec.cosmicbaseballinit.controller;

import com.hyunec.cosmicbaseballinit.model.BattingResult;
import com.hyunec.cosmicbaseballinit.service.BattingServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Controller
@RequiredArgsConstructor
public class BattingController {

    private final BattingServiceImpl battingServiceImpl;

    @GetMapping("/batting")
    public ResponseEntity<BattingResult> getBattingResult() {
        BattingResult result = battingServiceImpl.batting();

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
