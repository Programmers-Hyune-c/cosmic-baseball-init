package com.hyunec.cosmicbaseballinit.contoller;

import com.hyunec.cosmicbaseballinit.domain.BattingResult;
import com.hyunec.cosmicbaseballinit.dto.ResponseDto;
import com.hyunec.cosmicbaseballinit.service.BattingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CosmicBaseballController {

    private final BattingService battingService;

    @GetMapping("/batting")
    public ResponseDto batting() {
        BattingResult result = battingService.hit();
        return new ResponseDto(result.name());
    }
}
