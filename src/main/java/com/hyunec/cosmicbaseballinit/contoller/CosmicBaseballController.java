package com.hyunec.cosmicbaseballinit.contoller;

import com.hyunec.cosmicbaseballinit.domain.BattingResult;
import com.hyunec.cosmicbaseballinit.domain.BattingResultCount;
import com.hyunec.cosmicbaseballinit.dto.ResponseDto;
import com.hyunec.cosmicbaseballinit.service.BattingResultCountService;
import com.hyunec.cosmicbaseballinit.service.BattingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CosmicBaseballController {

    private final BattingService battingService;
    private final BattingResultCountService battingResultCountService;

    @GetMapping("/batting/start")
    public ResponseDto startBatting() {
        BattingResultCount battingResultCount = battingService.startBatting();
        return ResponseDto.of(battingResultCount);
    }

    @GetMapping("/batting/{id}")
    public ResponseDto batting(@PathVariable Long id) {
        BattingResult result = battingService.batting();
        BattingResultCount battingResultCount =
            battingResultCountService.calculateCount(id, result);
        battingResultCountService.updateBattingResultCount(id, battingResultCount);

        return ResponseDto.of(result.getName(), battingResultCount);
    }
}
