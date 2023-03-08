package com.hyunec.cosmicbaseballinit.service;

import com.hyunec.cosmicbaseballinit.domain.BattingResult;
import com.hyunec.cosmicbaseballinit.domain.BattingResultCount;
import org.springframework.stereotype.Service;

@Service
public class BattingResultCountService {

    public BattingResultCount calculateCount(BattingResult result) {
        BattingResultCount battingResultCount = BattingResultCount.getInstance();
        battingResultCount.addCount(result);
        return battingResultCount;
    }


}
