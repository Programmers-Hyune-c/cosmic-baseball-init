package com.hyunec.cosmicbaseballinit.roundGame.domain.repository;

import com.hyunec.cosmicbaseballinit.roundGame.persistence.dto.PastHitterGameResultDto;

import java.util.List;

public interface PastHitterGameResultListRepository {
    public List<PastHitterGameResultDto> getPastHitterGameResults();
    public void save(PastHitterGameResultDto pastHitterGameResultDto);
    public void init();
}
