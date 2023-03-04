package com.hyunec.cosmicbaseballinit.service;

import static com.hyunec.cosmicbaseballinit.domain.BattingResult.BALL;
import static com.hyunec.cosmicbaseballinit.domain.BattingResult.HIT;
import static com.hyunec.cosmicbaseballinit.domain.BattingResult.STRIKE;

import com.hyunec.cosmicbaseballinit.domain.BattingResult;
import com.hyunec.cosmicbaseballinit.util.RandomGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BattingService {

	private final RandomGenerator randomGenerator;

	public BattingResult hit() {
		return getResult();
	}

	private BattingResult getResult() {
		switch (randomGenerator.getRandomNumber()) {
			case 0:
				return STRIKE;
			case 1:
				return HIT;
			default:
				return BALL;
		}
	}
}
