package com.hyunec.cosmicbaseballinit.service;

import com.hyunec.cosmicbaseballinit.domain.BattingResult;
import com.hyunec.cosmicbaseballinit.domain.ScoreBoard;

public interface GameService {

    ScoreBoard newGame();

    ScoreBoard batting(Long id, int percentage, BattingResult battingResult);

}
