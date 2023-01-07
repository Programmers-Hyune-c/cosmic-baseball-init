package com.hyunec.cosmicbaseballinit.roundGame.domain.service;

import com.hyunec.cosmicbaseballinit.controller.HitterGameInterface;
import com.hyunec.cosmicbaseballinit.roundGame.domain.Round;
import com.hyunec.cosmicbaseballinit.roundGame.domain.repository.RoundRepository;
import com.hyunec.cosmicbaseballinit.roundGame.persistence.dto.OutAndScoreDto;
import com.hyunec.cosmicbaseballinit.vo.hitterGame.HitterResult;
import com.hyunec.cosmicbaseballinit.vo.hitterGame.HittingParamVo;
import com.hyunec.cosmicbaseballinit.vo.hitterGame.PitchProbabilitySettingVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoundGameService {
    private final HitterGameInterface hitterGameInterface;
    private final RoundRepository roundRepository;

    // 타구
    public String hit( HittingParamVo hittingParamVo) throws Exception{
        String hittingResult = hitterGameInterface.hitting(hittingParamVo);

        if(hitterGameInterface.isHitterGameEnd()) {
            //TODO: hittingResult에 따라 득점, 아웃 여부 판별하기
        }

        return hittingResult;
    }
}
