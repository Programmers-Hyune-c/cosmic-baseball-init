package com.hyunec.cosmicbaseballinit.service;

import com.hyunec.cosmicbaseballinit.domain.Ball;
import com.hyunec.cosmicbaseballinit.domain.BattingResult;
import com.hyunec.cosmicbaseballinit.domain.DoubleStrike;
import com.hyunec.cosmicbaseballinit.domain.Hit;
import com.hyunec.cosmicbaseballinit.domain.Strike;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.springframework.stereotype.Service;

@Service
public class BattingService {

    private static final List<BattingResult> BATTING_RESULTS =
                new ArrayList<>(List.of(new Strike(), new Hit(), new Ball(), new DoubleStrike()));

    public static final Random RANDOM = new Random();

    public BattingResult batting() {
        return BATTING_RESULTS.get(RANDOM.nextInt(BATTING_RESULTS.size()));
    }

}
