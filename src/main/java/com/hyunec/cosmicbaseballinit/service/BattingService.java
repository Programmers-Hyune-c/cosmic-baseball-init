package com.hyunec.cosmicbaseballinit.service;

import com.hyunec.cosmicbaseballinit.domain.BattingResult;
import java.util.Random;
import org.springframework.stereotype.Service;

@Service
public class BattingService {

	public static final Random RANDOM = new Random();

	public BattingResult batting() {
		return BattingResult.values()[RANDOM.nextInt(BattingResult.values().length)];
	}
}
