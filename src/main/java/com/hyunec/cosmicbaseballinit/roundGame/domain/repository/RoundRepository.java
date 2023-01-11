package com.hyunec.cosmicbaseballinit.roundGame.domain.repository;

import com.hyunec.cosmicbaseballinit.roundGame.persistence.dto.OutAndScoreDto;

public interface RoundRepository {

    public OutAndScoreDto getRoundScore();
    public void updateRoundScore(OutAndScoreDto outAndScoreDto);
    public void initRoundScore();
}
