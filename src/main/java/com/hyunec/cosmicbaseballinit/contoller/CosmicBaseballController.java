package com.hyunec.cosmicbaseballinit.contoller;

import com.hyunec.cosmicbaseballinit.domain.BattingResult;
import com.hyunec.cosmicbaseballinit.dto.ResponseDto;
import com.hyunec.cosmicbaseballinit.service.BattingService;
import com.hyunec.cosmicbaseballinit.service.ResultCountService;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CosmicBaseballController {

    private final BattingService battingService;
    private final ResultCountService resultCountService;

    @GetMapping("/batting")
    public ResponseDto startBatting() {

        BattingResult result = battingService.batting();

        resultCountService.addResultCount(result);
        Map<String, Integer> resultCount = resultCountService.getResultCount();

        return new ResponseDto(result.getName(), resultCount);
    }
}
