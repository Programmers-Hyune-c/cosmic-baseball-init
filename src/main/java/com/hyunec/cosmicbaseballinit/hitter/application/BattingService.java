package com.hyunec.cosmicbaseballinit.hitter.application;

import com.hyunec.cosmicbaseballinit.hitter.domain.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BattingService {

    private final BattingRepository battingRepository;

    public BattingResult swing(Batting batting, RandomGenerate randomGenerate) {
        battingRepository.save(batting);
        final Battings battings = new Battings(battingRepository.findAll(), randomGenerate);
        final BattingResult battingResult = battings.battingResult(batting);

        if (isBattingsClearable(battings, battingResult)) {
            battingRepository.clear();
        }

        return battingResult;
    }

    private boolean isBattingsClearable(final Battings battings, final BattingResult battingResult) {
        return battings.isNotEmpty() && battingResult.isClearable();
    }

}
