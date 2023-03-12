package com.hyunec.cosmicbaseballinit.dao;

import com.hyunec.cosmicbaseballinit.domain.TotalBattingResult;

public interface BattingResultCountDao {

    void save(TotalBattingResult totalBattingResult);
    void update(Long id, TotalBattingResult totalBattingResult);
    TotalBattingResult findById(Long id);

    void delete(Long id);
}
