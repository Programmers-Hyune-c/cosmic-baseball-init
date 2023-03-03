package com.hyunec.cosmicbaseballinit.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ComicBaseballService {

    private final BattingService battingService;
    private final BallCountService ballCountService;

    public BattingResult getBattingResult() {
        return battingService.hit();
    }

    public void getBallCount(BattingResult result){
        ballCountService.addBallCount(result);
    }
}
