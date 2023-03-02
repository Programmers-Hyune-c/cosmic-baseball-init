package com.hyunec.cosmicbaseballinit.domain;

import static com.hyunec.cosmicbaseballinit.domain.BattingResult.*;

import java.util.Random;

public class Batter {
	private Random random = new Random();

	public BattingResult hit() {
		return getResult();
	}

	private BattingResult getResult() {
		BattingResult result = null;
		int randomNumber = random.nextInt(3);
		switch (randomNumber) {
			case 0:
				result = STRIKE;
				break;
			case 1:
				result = HIT;
				break;
			default:
				result = BALL;
		}
		return result;
	}
}
