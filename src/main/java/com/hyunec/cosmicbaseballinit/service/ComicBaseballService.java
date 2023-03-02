package com.hyunec.cosmicbaseballinit.service;

import org.springframework.stereotype.Service;

import com.hyunec.cosmicbaseballinit.domain.Batter;
import com.hyunec.cosmicbaseballinit.domain.BattingResult;

@Service
public class ComicBaseballService {

	public BattingResult getBattingResult() {
		Batter batter = new Batter();
		return batter.hit();
	}
}
