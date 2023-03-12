package com.hyunec.cosmicbaseballinit.contoller;

import com.hyunec.cosmicbaseballinit.domain.TotalBattingResult;
import com.hyunec.cosmicbaseballinit.dto.ResponseDto;
import com.hyunec.cosmicbaseballinit.service.BattingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CosmicBaseballController {

    private final BattingService battingService;

    @PostMapping("/batting/start")
    public ResponseDto startBatting() {
        TotalBattingResult startBatting = battingService.startBatting();
        return new ResponseDto(startBatting);
    }

    @PatchMapping("/batting/{id}")
    public ResponseDto batting(@PathVariable Long id) {
        TotalBattingResult totalBattingResult = battingService.batting(id);
        return new ResponseDto(totalBattingResult);
    }

    @PostMapping("/batting/new/{id}")
    public ResponseDto newBatting(@PathVariable Long id) {
        TotalBattingResult newBatting = battingService.newBatting(id);
        return new ResponseDto(newBatting);
    }
}
