package com.hyunec.cosmicbaseballinit.roundGame.domain.service;

import com.hyunec.cosmicbaseballinit.controller.HitterGameInterface;
import com.hyunec.cosmicbaseballinit.roundGame.domain.Base;
import com.hyunec.cosmicbaseballinit.roundGame.domain.HitterGameResults;
import com.hyunec.cosmicbaseballinit.roundGame.domain.Round;
import com.hyunec.cosmicbaseballinit.roundGame.domain.repository.PastHitterGameResultListRepository;
import com.hyunec.cosmicbaseballinit.roundGame.domain.repository.RoundRepository;
import com.hyunec.cosmicbaseballinit.roundGame.domain.vo.HitterGameResult;
import com.hyunec.cosmicbaseballinit.roundGame.domain.vo.PitchResultAndCountVo;
import com.hyunec.cosmicbaseballinit.roundGame.persistence.dto.OutAndScoreDto;
import com.hyunec.cosmicbaseballinit.roundGame.persistence.dto.PastHitterGameResultDto;
import com.hyunec.cosmicbaseballinit.vo.hitterGame.HitterResult;
import com.hyunec.cosmicbaseballinit.vo.hitterGame.HittingRequestVo;
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

    /**
     * 타구
     * @param hittingRequestVo : 타구 결과에 반영되는 확률
     * @return String : 상황에 따라 PitchResult 또는 HitterResult를 String으로 변환하여 반환
     * @throws Exception
     */
    public String hit( HittingRequestVo hittingRequestVo) throws Exception{
        String hittingResult = hitterGameInterface.hitting(hittingRequestVo);

        if(hitterGameInterface.isHitterGameEnd()) {
            // hittingResult에 따라 득점, 아웃 여부 판별하기
            judgeByHitterResult(hittingResult);

            // 결과 저장하기
            saveHitterGameResult(hittingResult);
        }
        return hittingResult;
    }

    /**
     * hittingResult에 따라 득점, 아웃 여부 판별하기
     * @param hitterResult
     */
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

    /**
     * HittingGame 결과 저장
     * @param hittingResult : hittingGame의 결과
     */
    private void saveHitterGameResult(String hittingResult) {
        Map<PitchResult, Integer> pitchResultIntegerMap = hitterGameInterface.hitterScore();
        PitchResultAndCountVo pitchResultAndCountVo = new PitchResultAndCountVo(pitchResultIntegerMap);
        HitterResult hitterGameResult = hitterGameInterface.getHitterGameResult();

        // PastHitterGameResult 생성
        HitterGameResult pastHitterGameResult = new HitterGameResult(pitchResultAndCountVo, hitterGameResult);

        // save
        pastHitterResultRepository.save(new PastHitterGameResultDto(pastHitterGameResult));

        // hitterGame 초기화
        hitterGameInterface.initScore();
    }

    /**
     * 아웃 + 1 후 저장
     */
    public void out() {
        OutAndScoreDto now = roundRepository.getRoundScore();
        roundRepository.updateRoundScore(new OutAndScoreDto(now.getOutCount() + 1,
                now.getScoreCount()));
    }

    /**
     * 진루
     */
    public void advancingBase() {
        Integer hitterCountInHomeBase = baseService.advancingBase();
        if (hitterCountInHomeBase > 0) {
            gotScored(hitterCountInHomeBase);
        }
    }

    /**
     * 득점
     * @param score : 추가할 점수
     */
    private void gotScored(Integer score) {
        // 현재 round 상황 조회
        OutAndScoreDto roundDto = roundRepository.getRoundScore();
        Round roundNow = new Round(roundDto.getOutCount(), roundDto.getScoreCount());

        // 득점
        Round roundAfterPlusScore = roundNow.score(score);

        // update
        OutAndScoreDto roundDtoAfterScored = new OutAndScoreDto(
                roundAfterPlusScore.getOutCount(), roundAfterPlusScore.getScoreCount());
        roundRepository.updateRoundScore(roundDtoAfterScored);
    }


    /**
     * 홈런 일시 점수 추가 (현재 베이스 인원수 + 진루할 인원 1명)
     */
    public void plusScoreWhenHomerun() {
        Base bases = baseService.getBases();
        Integer countOfHitterInBase = bases.size(); // 베이스에 나가 있는 총 인원 수
        // 득점
        gotScored(countOfHitterInBase + 1);
        // Bases 초기화
        baseService.init();
    }

    public void init() {
        roundRepository.initRoundScore();
        baseService.init();
        hitterGameInterface.initScore();
    }

    public HitterGameResults getHitterGameResults() {

        List<HitterGameResult> returnList = new ArrayList<>();

        // 과거 HitterResult
        List<PastHitterGameResultDto> pastDtos = pastHitterResultRepository.getPastHitterGameResults();
        // dto -> PastHitterGameResult
        for (PastHitterGameResultDto dto : pastDtos) {
            HitterGameResult pastResult = dto.get();
            returnList.add(new HitterGameResult(
                    pastResult.getPitchResultAndCountVo()
                    , pastResult.getHitterResult()));
        }

        // 지금 진행되는 hitting 상태 merge
        Map<PitchResult, Integer> nowHitteringStatus = hitterGameInterface.hitterScore();
        PitchResultAndCountVo nowPitchResultAndCountVo = new PitchResultAndCountVo(nowHitteringStatus);
        HitterGameResult nowHitterGameResult = new HitterGameResult(nowPitchResultAndCountVo, null);
        returnList.add(nowHitterGameResult);

        return new HitterGameResults(returnList);
    }

    public Round getScoreAndOut() {
        OutAndScoreDto roundScore = roundRepository.getRoundScore();
        return new Round(roundScore.getOutCount(), roundScore.getScoreCount());
    }
}
