package com.hyunec.cosmicbaseballinit.service;

import com.hyunec.cosmicbaseballinit.model.BatterStatus;
import com.hyunec.cosmicbaseballinit.model.BattingResult;

public interface BattingService {
    BattingResult batting();

    BatterStatus getBatterStatus();
}
