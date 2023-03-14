package com.hyunec.cosmicbaseballinit.service;

import com.hyunec.cosmicbaseballinit.dao.TotalBattingResultDao;
import com.hyunec.cosmicbaseballinit.domain.BattingResult;
import com.hyunec.cosmicbaseballinit.domain.TotalBattingResult;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BattingService {

    private final TotalBattingResultDao totalBattingResultDao;

    public static final Random RANDOM = new Random();

    public TotalBattingResult startBatting() {
        TotalBattingResult startBatting = new TotalBattingResult();
        return totalBattingResultDao.save(startBatting);
    }

    public TotalBattingResult batting(Long id) {
        TotalBattingResult totalBattingResultEntity = totalBattingResultDao.findById(id);
        BattingResult result = BattingResult.values()[RANDOM.nextInt(BattingResult.values().length)];

        totalBattingResultEntity.setBattingResult(result);
        totalBattingResultEntity.addBattingResultCount(result);
        totalBattingResultEntity.judgeBatterStatus();

        return totalBattingResultDao.update(totalBattingResultEntity);
    }


}
