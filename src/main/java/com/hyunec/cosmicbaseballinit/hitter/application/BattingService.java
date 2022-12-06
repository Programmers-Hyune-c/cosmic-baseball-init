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
        Battings battings = new Battings(battingRepository.findAll(), randomGenerate);

        BattingResult battingResult = battings.battingResult(batting);

        if(battingResult.isClear()) {
            battingRepository.clear();
        }

        return battingResult;
    }

}
