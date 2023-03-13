package com.hyunec.cosmicbaseballinit.service;

import static com.hyunec.cosmicbaseballinit.domain.BatterStatus.ON_GOING;

import com.hyunec.cosmicbaseballinit.dao.TotalBattingResultDao;
import com.hyunec.cosmicbaseballinit.domain.BattingResult;
import com.hyunec.cosmicbaseballinit.domain.TotalBattingResult;
import com.hyunec.cosmicbaseballinit.exception.NewBattingException;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BattingService {

    private final TotalBattingResultDao totalBattingResultDao;
    private final AtomicLong sequence = new AtomicLong(0);

    public static final Random RANDOM = new Random();

    public TotalBattingResult startBatting() {
        long id = sequence.incrementAndGet();
        TotalBattingResult startBatting = new TotalBattingResult(id);

        return totalBattingResultDao.save(startBatting);
    }

    public TotalBattingResult batting(Long id) {
        TotalBattingResult totalBattingResultEntity = totalBattingResultDao.findById(id);
        BattingResult result = BattingResult.values()[RANDOM.nextInt(BattingResult.values().length)];

        totalBattingResultEntity.setBattingResult(result);
        totalBattingResultEntity.addBattingResultCount(result);
        totalBattingResultEntity.judgeBatterStatus();

        return totalBattingResultDao.update(id, totalBattingResultEntity);
    }

    public TotalBattingResult newBatting(Long id) {
        if (isOnGoing(id)) {
            throw new NewBattingException("새로운 타석 안됨");
        }
        totalBattingResultDao.delete(id);
        return startBatting();
    }

    private boolean isOnGoing(Long id) {
        TotalBattingResult totalBattingResultEntity = totalBattingResultDao.findById(id);
        return totalBattingResultEntity.getBatterStatus() == ON_GOING;
    }
}
