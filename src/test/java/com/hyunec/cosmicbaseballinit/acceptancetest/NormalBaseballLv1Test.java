package com.hyunec.cosmicbaseballinit.acceptancetest;

import static org.assertj.core.api.Assertions.*;

import java.util.Random;

import org.assertj.core.data.Percentage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import com.hyunec.cosmicbaseballinit.domain.Batter;
import com.hyunec.cosmicbaseballinit.domain.BattingResult;

class NormalBaseballLv1Test {

	private Random random;


	@BeforeEach
	void init() {
		random = new Random();
	}

	@DisplayName("타격 결과는 모두 같은 확률을 가집니다.")
	@RepeatedTest(10)
	void t1() {
		Batter batter = new Batter();
		BattingResult battingResult = batter.hit();
		int count = 0;
		count = getBattingResultCount(battingResult.ordinal() , count);

		assertThat(count).isCloseTo(33_300, Percentage.withPercentage(1));
	}

	@DisplayName("타격 결과는 strike, ball, hit 입니다.")
	@Test
	void t2() {
		throw new RuntimeException("Not yet implemented");
	}

	private int getBattingResultCount(int resultNumber, int count) {
		for (int i = 0; i < 100_000; i++) {
			int randomNumber = random.nextInt(3);
			count = addCount(resultNumber, count, randomNumber);
		}
		return count;
	}

	private static int addCount(final int resultNumber, int count, final int randomNumber) {
		if (resultNumber == randomNumber) {
			count++;
		}
		return count;
	}
}
