package com.hyunec.cosmicbaseballinit.service;

import static com.hyunec.cosmicbaseballinit.domain.BatterStatus.ON_GOING;

import com.hyunec.cosmicbaseballinit.dao.BattingResultCountDao;
import com.hyunec.cosmicbaseballinit.domain.BattingResult;
import com.hyunec.cosmicbaseballinit.domain.TotalBattingResult;
import com.hyunec.cosmicbaseballinit.exception.NewBattingException;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BattingService {

    private final BattingResultCountDao battingResultCountDao;

    public static final Random RANDOM = new Random();

    public TotalBattingResult startBatting() {
        TotalBattingResult totalBattingResult = TotalBattingResult.getInstance();
        battingResultCountDao.save(totalBattingResult);

        return totalBattingResult;
    }

    public TotalBattingResult batting(Long id) {
        TotalBattingResult totalBattingResultEntity = battingResultCountDao.findById(id);
        BattingResult result = BattingResult.values()[RANDOM.nextInt(BattingResult.values().length)];

        totalBattingResultEntity.setBattingTotalResult(result);
        battingResultCountDao.update(id, totalBattingResultEntity);

        return totalBattingResultEntity;
    }

    public TotalBattingResult newBatting( Long id) {
        if (isOnGoing(id)) {
            throw new NewBattingException("새로운 타석 안됨");
        }
        battingResultCountDao.delete(id);
        return startBatting();
    }

    private boolean isOnGoing(Long id) {
        TotalBattingResult totalBattingResultEntity = battingResultCountDao.findById(id);
        return totalBattingResultEntity.getBatterStatus() == ON_GOING;
    }
}
