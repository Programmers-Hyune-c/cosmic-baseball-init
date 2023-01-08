package com.hyunec.cosmicbaseballinit.roundGame.persistence.dao;

import com.hyunec.cosmicbaseballinit.roundGame.domain.repository.PastHitterGameResultListRepository;
import com.hyunec.cosmicbaseballinit.roundGame.persistence.dto.PastHitterGameResultDto;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PastHitterGameResultListDao implements PastHitterGameResultListRepository {
    private List<PastHitterGameResultDto> pastHitterGameResults;

    @Override
    public List<PastHitterGameResultDto> getPastHitterGameResults() {
        return pastHitterGameResults;
    }

    @Override
    public void save(PastHitterGameResultDto pastHitterGameResultDto) {
        pastHitterGameResults.add(pastHitterGameResultDto);
    }

    @Override
    public void init() {
        pastHitterGameResults.clear();
    }
}
