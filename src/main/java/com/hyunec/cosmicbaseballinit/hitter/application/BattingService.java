package com.hyunec.cosmicbaseballinit.hitter.application;

import com.hyunec.cosmicbaseballinit.hitter.domain.Batting;
import com.hyunec.cosmicbaseballinit.hitter.domain.BattingRepository;
import com.hyunec.cosmicbaseballinit.hitter.domain.Battings;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BattingService {

    private final BattingRepository battingRepository;

    public String swing(Batting batting) {
        battingRepository.save(batting);
        final Battings battings = new Battings(battingRepository.findAll());
        final String battingResult = battings.battingResult();

        if (battings.isFinishPlaceApperances(battingResult)) {
            battingRepository.clear();
        }

        return battingResult;
    }

}
