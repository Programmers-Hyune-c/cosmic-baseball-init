package com.hyunec.cosmicbaseballinit.service;

import static com.hyunec.cosmicbaseballinit.domain.BattingResult.BALL;
import static com.hyunec.cosmicbaseballinit.domain.BattingResult.HIT;
import static com.hyunec.cosmicbaseballinit.domain.BattingResult.STRIKE;

import com.hyunec.cosmicbaseballinit.domain.BattingResult;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BattingService {
	private final Random random;

	public BattingResult hit() {
		return getResult();
	}

	private BattingResult getResult() {
		switch (random.nextInt(BattingResult.values().length)) {
			case 0:
				return STRIKE;
			case 1:
				return HIT;
			default:
				return BALL;
		}
	}
}
