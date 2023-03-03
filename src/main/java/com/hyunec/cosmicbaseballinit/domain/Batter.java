package com.hyunec.cosmicbaseballinit.domain;

import static com.hyunec.cosmicbaseballinit.domain.BattingResult.*;

import java.util.Random;

import org.springframework.stereotype.Component;

import com.hyunec.cosmicbaseballinit.util.NumberFactory;
@Component
public class Batter {

	private final Random random = NumberFactory.getInstance();

	public BattingResult hit() {
		return getResult();
	}

	private BattingResult getResult() {
		switch (random.nextInt(3)) {
			case 0:
				return STRIKE;
			case 1:
				return HIT;
			default:
				return BALL;
		}
	}
}
