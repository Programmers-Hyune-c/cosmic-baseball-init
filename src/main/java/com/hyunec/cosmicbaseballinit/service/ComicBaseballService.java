package com.hyunec.cosmicbaseballinit.service;

import org.springframework.stereotype.Service;

import com.hyunec.cosmicbaseballinit.domain.Batter;
import com.hyunec.cosmicbaseballinit.domain.BattingResult;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ComicBaseballService {

	private final Batter batter;

	public BattingResult getBattingResult() {
		return batter.hit();
	}
}
