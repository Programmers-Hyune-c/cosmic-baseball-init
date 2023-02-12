package com.hyunec.cosmicbaseballinit.domain.baseball.model;

import lombok.Getter;

import java.util.Arrays;
import java.util.Map;
import java.util.Random;

@Getter

public enum Batting {
	STRIKE,HIT, BALL;
	private static final Random random = new Random();

	public static Batting generate() {
		int nResult = random.nextInt(3) + 1;
		if(nResult == 1) {
			return STRIKE;
		} else if(nResult == 2) {
			return BALL;
		} else {
			return HIT;
		}
	}


}
