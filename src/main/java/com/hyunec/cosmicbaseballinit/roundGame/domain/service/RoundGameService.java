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
            // hittingResult에 따라 득점, 아웃 여부 판별하기
            judgeByHitterResult(hittingResult);
        }

        return hittingResult;
    }

    // hittingResult에 따라 득점, 아웃 여부 판별하기
    public void judgeByHitterResult(String hitterResult) {
        if (hitterResult.equals(HitterResult.STRIKE_OUT.name())
                || hitterResult.equals(HitterResult.BULLSEYE_STRIKE.name())) {
            outPlusOne();   // 아웃
            return;
        }
        if (hitterResult.equals(HitterResult.FOUR_BALL.name())
                || hitterResult.equals(HitterResult.BULLSEYE_BALL.name())
                || hitterResult.equals(HitterResult.HIT.name())) {
            advancingBase();    // 진루
            return;
        }
        if (hitterResult.equals(HitterResult.HOMERUN.name())) {
            gotScored(); // 득점
        }
    }

    // 아웃
    public void outPlusOne() {
        OutAndScoreDto now = roundRepository.getRoundScore();
        roundRepository.updateRoundScore(new OutAndScoreDto(now.getOutCount() + 1,
                now.getScoreCount()));
    }

    // 진루
    public void advancingBase() {
    }

    // 득점
    public void gotScored() {

    }
}
