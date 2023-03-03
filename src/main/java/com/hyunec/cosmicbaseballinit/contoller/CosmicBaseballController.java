package com.hyunec.cosmicbaseballinit.contoller;

import com.hyunec.cosmicbaseballinit.domain.BattingResult;
import com.hyunec.cosmicbaseballinit.service.ComicBaseballService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CosmicBaseballController {

    private final ComicBaseballService baseballService;

    @GetMapping("/batting")
    public ResponseEntity<String> hit() {
        BattingResult result = baseballService.getBattingResult();
        return new ResponseEntity<>(result.name(), HttpStatus.OK);
    }
}
