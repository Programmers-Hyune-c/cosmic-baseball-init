package com.hyunec.cosmicbaseballinit.service;

import com.hyunec.cosmicbaseballinit.dao.BattingResultCountDao;
import com.hyunec.cosmicbaseballinit.domain.Ball;
import com.hyunec.cosmicbaseballinit.domain.BattingResult;
import com.hyunec.cosmicbaseballinit.domain.BattingResultCount;
import com.hyunec.cosmicbaseballinit.domain.DoubleBall;
import com.hyunec.cosmicbaseballinit.domain.DoubleStrike;
import com.hyunec.cosmicbaseballinit.domain.Hit;
import com.hyunec.cosmicbaseballinit.domain.Strike;
import java.util.List;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BattingService {

    private final BattingResultCountDao battingResultCountDao;

    private static final List<BattingResult> battingResult =
        BattingResultGenerator.of(
            new Strike(), new Ball(), new Hit(), new DoubleStrike(), new DoubleBall()
        );

    public static final Random RANDOM = new Random();

    public BattingResult batting() {
        return battingResult.get(RANDOM.nextInt(battingResult.size()));
    }

    public BattingResultCount startBatting() {
        BattingResultCount battingResultCount = BattingResultCount.getInstance();
        battingResultCountDao.save(battingResultCount);
        return battingResultCount;
    }
}
