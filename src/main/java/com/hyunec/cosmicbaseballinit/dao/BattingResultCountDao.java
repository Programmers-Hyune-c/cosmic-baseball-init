package com.hyunec.cosmicbaseballinit.dao;

import com.hyunec.cosmicbaseballinit.domain.BattingResultCount;

public interface BattingResultCountDao {

    void save(BattingResultCount battingResultCount);
    void update(Long id, BattingResultCount battingResultCount);
    BattingResultCount findById(Long id);
}
