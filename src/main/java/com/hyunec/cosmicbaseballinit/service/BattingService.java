package com.hyunec.cosmicbaseballinit.service;

import com.hyunec.cosmicbaseballinit.common.RandomUtils;
import com.hyunec.cosmicbaseballinit.model.BattingResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BattingService {

    private final RandomUtils randomUtils;

    public BattingResult batting() {
        return randomUtils.getRandomBattingResult();
    }
}
