package com.hyunec.cosmicbaseballinit.service;

import com.hyunec.cosmicbaseballinit.dao.TotalBattingResultDao;
import com.hyunec.cosmicbaseballinit.domain.BattingResult;
import com.hyunec.cosmicbaseballinit.domain.TotalBattingResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class BattingService {

    private final TotalBattingResultDao totalBattingResultDao;

    public TotalBattingResult newBatting() {
        return totalBattingResultDao.save(TotalBattingResult.of(0, 0));
    }

    public TotalBattingResult batting(Long id, int percentage, BattingResult targetResult) {
        BattingResult battingResult =
            RandomBattingResultGenerator.getBattingResult(percentage, targetResult);
        return updateTotalBattingResult(id, battingResult);
    }

    private TotalBattingResult updateTotalBattingResult(
        Long id,
        BattingResult result
    ) {
        TotalBattingResult totalBattingResultEntity = totalBattingResultDao.findById(id);
        totalBattingResultEntity.adjustAccordingToBattingResult(result);
        return totalBattingResultEntity;
    }
}
