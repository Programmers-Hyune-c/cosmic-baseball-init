package com.hyunec.cosmicbaseballinit.hitter.application;

import com.hyunec.cosmicbaseballinit.hitter.domain.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BattingService {

    private final BattingRepository battingRepository;

    public Battings swing(Batting batting) {
        final Battings battings = new Battings(battingRepository.findAll());
        battingRepository.save(batting);
        battings.addBatting(batting);

        return battings;
    }

    public void clearBattings(String battingResult) {
        if(BattingResult.isFinishBaseBall(battingResult)) {
            battingRepository.clear();
        }
    }

}
