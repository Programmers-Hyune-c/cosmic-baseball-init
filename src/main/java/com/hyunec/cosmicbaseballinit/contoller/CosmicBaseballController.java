package com.hyunec.cosmicbaseballinit.contoller;

import static com.hyunec.cosmicbaseballinit.service.BallCountService.COUNT_STORE;

import com.hyunec.cosmicbaseballinit.domain.BattingResult;
import com.hyunec.cosmicbaseballinit.dto.ResponseDto;
import com.hyunec.cosmicbaseballinit.service.BallCountService;
import com.hyunec.cosmicbaseballinit.service.BattingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CosmicBaseballController {

    private final BattingService battingService;
    private final BallCountService ballCountService;

    @GetMapping("/batting")
    public ResponseDto startBatting() {
        BattingResult result = battingService.batting();
        ballCountService.setBallCount(result);

        return new ResponseDto(result.name(), COUNT_STORE);
    }
}
