package com.hyunec.cosmicbaseballinit.dao;

import com.hyunec.cosmicbaseballinit.domain.ScoreBoard;

public interface ScoreBoardDao {

    ScoreBoard save(ScoreBoard scoreBoard);
    ScoreBoard findById(Long id);
}
