package com.hyunec.cosmicbaseballinit.roundGame.domain.service;

import com.hyunec.cosmicbaseballinit.controller.HitterGameInterface;
import com.hyunec.cosmicbaseballinit.roundGame.domain.PastHitterGameResultList;
import com.hyunec.cosmicbaseballinit.roundGame.domain.repository.PastHitterGameResultListRepository;
import com.hyunec.cosmicbaseballinit.roundGame.domain.repository.RoundRepository;
import com.hyunec.cosmicbaseballinit.roundGame.domain.vo.PastHitterGameResult;
import com.hyunec.cosmicbaseballinit.roundGame.domain.vo.PitchResultAndCountVo;
import com.hyunec.cosmicbaseballinit.roundGame.persistence.dto.OutAndScoreDto;
import com.hyunec.cosmicbaseballinit.roundGame.persistence.dto.PastHitterGameResultDto;
import com.hyunec.cosmicbaseballinit.vo.hitterGame.HitterResult;
import com.hyunec.cosmicbaseballinit.vo.hitterGame.HittingParamVo;
import com.hyunec.cosmicbaseballinit.vo.hitterGame.PitchResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class RoundGameService {
    private final HitterGameInterface hitterGameInterface;
    private final RoundRepository roundRepository;
    private final BaseService baseService;
    private final PastHitterGameResultListRepository pastHitterResultRepository;

    // 타구
    public String hit( HittingParamVo hittingParamVo) throws Exception{
        String hittingResult = hitterGameInterface.hitting(hittingParamVo);

        if(hitterGameInterface.isHitterGameEnd()) {
            // hittingResult에 따라 득점, 아웃 여부 판별하기
            judgeByHitterResult(hittingResult);

            // 결과 저장하기
            saveHitterGameResult(hittingResult);
        }
        return hittingResult;
    }
    
    // hittingResult에 따라 득점, 아웃 여부 판별하기
    public void judgeByHitterResult(String hitterResult) {
        if (hitterResult.equals(HitterResult.STRIKE_OUT.name())
                || hitterResult.equals(HitterResult.BULLSEYE_STRIKE.name())) {
            out();   // 아웃
            return;
        }
        if (hitterResult.equals(HitterResult.FOUR_BALL.name())
                || hitterResult.equals(HitterResult.BULLSEYE_BALL.name())
                || hitterResult.equals(HitterResult.HIT.name())) {
            advancingBase();    // 진루
            return;
        }
        if (hitterResult.equals(HitterResult.HOMERUN.name())) {
            plusScoreWhenHomerun(); // 홈런 시 득점
        }
    }

    // HitterGame결과 저장
    private void saveHitterGameResult(String hittingResult) {
        Map<PitchResult, Integer> pitchResultIntegerMap = hitterGameInterface.hitterScore();
        PitchResultAndCountVo pitchResultAndCountVo = new PitchResultAndCountVo(pitchResultIntegerMap);
        HitterResult hitterGameResult = hitterGameInterface.getHitterGameResult();

        // PastHitterGameResult 생성
        PastHitterGameResult pastHitterGameResult = new PastHitterGameResult(pitchResultAndCountVo, hitterGameResult);

        // save
        pastHitterResultRepository.save(new PastHitterGameResultDto(pastHitterGameResult));

        // hitterGame 초기화
        hitterGameInterface.initScore();
    }

    // 아웃
    public void out() {
        OutAndScoreDto now = roundRepository.getRoundScore();
        roundRepository.updateRoundScore(new OutAndScoreDto(now.getOutCount() + 1,
                now.getScoreCount()));
    }

    // 진루
    public void advancingBase() {
        baseService.advancingBase();
    }

    // 홈런시 득점 로직
    public void plusScoreWhenHomerun() {
        baseService.homerun();
    }

    public void init() {
        roundRepository.initRoundScore();
        baseService.init();
        hitterGameInterface.initScore();
    }

    public PastHitterGameResultList getScore() {

        List<PastHitterGameResult> returnList = new ArrayList<>();

        // 과거 HitterResult
        List<PastHitterGameResultDto> pastDtos = pastHitterResultRepository.getPastHitterGameResults();
        // dto -> PastHitterGameResult
        for (PastHitterGameResultDto dto : pastDtos) {
            PastHitterGameResult pastResult = dto.get();
            returnList.add(new PastHitterGameResult(
                    pastResult.getPitchResultAndCountVo()
                    , pastResult.getHitterResult()));
        }

        // 지금 진행되는 hitting 상태 merge
        Map<PitchResult, Integer> nowHitteringStatus = hitterGameInterface.hitterScore();
        PitchResultAndCountVo nowPitchResultAndCountVo = new PitchResultAndCountVo(nowHitteringStatus);
        PastHitterGameResult nowHitterGameResult = new PastHitterGameResult(nowPitchResultAndCountVo, null);
        returnList.add(nowHitterGameResult);

        return new PastHitterGameResultList(returnList);
    }
}
