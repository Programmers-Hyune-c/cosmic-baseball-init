package com.hyunec.cosmicbaseballinit.service;

import static com.hyunec.cosmicbaseballinit.domain.BattingResult.BALL;
import static com.hyunec.cosmicbaseballinit.domain.BattingResult.BULL_EYE_BALL;
import static com.hyunec.cosmicbaseballinit.domain.BattingResult.BULL_EYE_STRIKE;
import static com.hyunec.cosmicbaseballinit.domain.BattingResult.STRIKE;

import com.hyunec.cosmicbaseballinit.dao.TotalBattingResultDao;
import com.hyunec.cosmicbaseballinit.domain.BattingResult;
import com.hyunec.cosmicbaseballinit.domain.TotalBattingResult;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class BattingService {

    private final TotalBattingResultDao totalBattingResultDao;

    public static final Random RANDOM = new Random();

    public TotalBattingResult newBatting() {
        return totalBattingResultDao.save(new TotalBattingResult());
    }

    public TotalBattingResult batting(Long id) {
        TotalBattingResult updatedTotalBattingResult
            = updateTotalBattingResult(id, getBattingResult());
        return totalBattingResultDao.update(updatedTotalBattingResult);
    }

    private TotalBattingResult updateTotalBattingResult(
                                                            Long id,
                                                            BattingResult result
    ) {
        TotalBattingResult totalBattingResultEntity = totalBattingResultDao.findById(id);
        totalBattingResultEntity.setBattingResult(result);
        totalBattingResultEntity.addBattingResultCount(result);
        totalBattingResultEntity.judgeBatterStatus();
        return totalBattingResultEntity;
    }

    private BattingResult getBattingResult() {
        BattingResult result = getRandomBattingResult();
        switch (result) {
            case STRIKE:
                return isBullEyeStrike() ? BULL_EYE_STRIKE : STRIKE;
            case BALL:
                return isBullEyeBall() ? BULL_EYE_BALL : BALL;
            default:
                return result;
        }
    }

        private boolean isBullEyeStrike() {
            return RANDOM.nextInt(5) == BULL_EYE_STRIKE.ordinal();
        }

        private boolean isBullEyeBall() {
            return RANDOM.nextInt(5) == BULL_EYE_BALL.ordinal();
        }

        private BattingResult getRandomBattingResult() {
            return BattingResult.values()[getRandomNumber()];
        }

            private int getRandomNumber() {
                return RANDOM.nextInt(BattingResult.values().length);
            }
}
