package com.hyunec.cosmicbaseballinit;

import static com.hyunec.cosmicbaseballinit.BattingResult.*;

import java.util.Random;

public class Batter {
	private Random random = new Random();

	public BattingResult hit() {
		return getBattingResult();
	}

	private BattingResult getBattingResult() {
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
