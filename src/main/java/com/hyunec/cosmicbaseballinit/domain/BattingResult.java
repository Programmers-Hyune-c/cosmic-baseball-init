package com.hyunec.cosmicbaseballinit.domain;

import lombok.Getter;

@Getter
public enum BattingResult {
	STRIKE(0), HIT(1), BALL(2);

	private final int index;

	BattingResult(int index) {
		this.index = index;
	}
}
