package com.hyunec.cosmicbaseballinit.dao;

import com.hyunec.cosmicbaseballinit.domain.TotalBattingResult;

public interface TotalBattingResultDao {

    TotalBattingResult save(TotalBattingResult totalBattingResult);
    TotalBattingResult update(TotalBattingResult totalBattingResult);
    TotalBattingResult findById(Long id);
    void delete(Long id);
}
