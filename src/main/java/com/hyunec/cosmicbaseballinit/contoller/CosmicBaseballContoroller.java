package com.hyunec.cosmicbaseballinit.contoller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hyunec.cosmicbaseballinit.domain.BattingResult;
import com.hyunec.cosmicbaseballinit.service.ComicBaseballService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class CosmicBaseballContoroller {
	private final ComicBaseballService baseballService;

	@GetMapping("/batting")
	public ResponseEntity hit() {
		BattingResult result = baseballService.getBattingResult();
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
}
