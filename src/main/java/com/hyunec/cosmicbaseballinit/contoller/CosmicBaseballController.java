package com.hyunec.cosmicbaseballinit.contoller;

import com.hyunec.cosmicbaseballinit.domain.BattingResult;
import com.hyunec.cosmicbaseballinit.service.BattingService;
import com.hyunec.cosmicbaseballinit.dto.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CosmicBaseballController {
	private final BattingService battingService;

	@GetMapping("/batting")
	@ResponseStatus(HttpStatus.OK)
	public ResponseDto hit() {
		BattingResult result = battingService.hit();
		return new ResponseDto(result.name());
	}
}
