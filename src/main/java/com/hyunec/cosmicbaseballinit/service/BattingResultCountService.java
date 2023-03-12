package com.hyunec.cosmicbaseballinit.service;

import com.hyunec.cosmicbaseballinit.dao.BattingResultCountDao;
import com.hyunec.cosmicbaseballinit.domain.BattingResult;
import com.hyunec.cosmicbaseballinit.domain.BattingResultCount;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BattingResultCountService {

    private final BattingResultCountDao battingResultCountDao;

    public BattingResultCount calculateCount(Long id, BattingResult result) {
        BattingResultCount battingResultCountEntity = battingResultCountDao.findById(id);
        battingResultCountEntity.addCount(result);
        return battingResultCountEntity;
    }

    public void updateBattingResultCount(Long id, BattingResultCount battingResultCount) {
        battingResultCountDao.update(id, battingResultCount);
    }
}
