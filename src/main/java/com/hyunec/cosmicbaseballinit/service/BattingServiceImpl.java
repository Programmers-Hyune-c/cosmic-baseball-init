package com.hyunec.cosmicbaseballinit.service;

import com.hyunec.cosmicbaseballinit.common.RandomGenerator;
import com.hyunec.cosmicbaseballinit.model.BattingResult;
import com.hyunec.cosmicbaseballinit.model.PlateStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BattingServiceImpl implements BattingService {

    @Override
    public BattingResult batting() {
        int value = RandomGenerator.RANDOM.nextInt(5);
        return BattingResult.getBattingResult(value);
    }

    @Override
    public PlateStatus getPlateStatus() throws Exception {
        PlateStatus plateStatus = new PlateStatus();
        if (plateStatus.checkInitialStatus()) {
            BattingResult result = batting();
            plateStatus.updateBatterResult(result);
            return plateStatus;
        } else {
            throw new Exception();
        }
    }
}
