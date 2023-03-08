package com.hyunec.cosmicbaseballinit.contoller;

import com.hyunec.cosmicbaseballinit.domain.BattingResult;
import com.hyunec.cosmicbaseballinit.dto.ResponseDto;
import com.hyunec.cosmicbaseballinit.domain.BattingResultCount;
import com.hyunec.cosmicbaseballinit.service.BattingResultCountService;
import com.hyunec.cosmicbaseballinit.service.BattingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CosmicBaseballController {

    private final BattingService battingService;
    private final BattingResultCountService battingResultCountService;

    @GetMapping("/batting")
    public ResponseDto startBatting() {
        BattingResult result = battingService.batting();
        BattingResultCount battingResultCount = battingResultCountService.calculateCount(result);

        return ResponseDto.builder()
                            .ballCount(battingResultCount.getBallCount())
                            .strikeCount(battingResultCount.getStrikeCount())
                            .result(result.getName())
                            .build();
    }
}
