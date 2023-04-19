package com.hyunec.cosmicbaseballinit.service;

import com.hyunec.cosmicbaseballinit.model.BattingResult;
import com.hyunec.cosmicbaseballinit.model.PlateStatus;

public interface BattingService {
    BattingResult batting();

    PlateStatus updatePlateStatus(PlateStatus plateStatus);
}
