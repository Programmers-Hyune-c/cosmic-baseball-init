package com.hyunec.cosmicbaseballinit.contoller;


import static com.hyunec.cosmicbaseballinit.service.BallCountService.COUNT_STORE;

import com.hyunec.cosmicbaseballinit.dto.ResponseDto;
import com.hyunec.cosmicbaseballinit.service.BattingResult;
import com.hyunec.cosmicbaseballinit.service.ComicBaseballService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CosmicBaseballController {

    private final ComicBaseballService baseballService;

    @GetMapping("/batting")
    public ResponseEntity<ResponseDto> hit() {

        BattingResult result = baseballService.getBattingResult();
        baseballService.getBallCount(result);
        ResponseDto responseDto = new ResponseDto(COUNT_STORE);

        return ResponseEntity.ok().body(responseDto);
    }
}
