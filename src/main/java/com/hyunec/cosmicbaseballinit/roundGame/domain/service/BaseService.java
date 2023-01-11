package com.hyunec.cosmicbaseballinit.roundGame.domain.service;

import com.hyunec.cosmicbaseballinit.roundGame.domain.Base;
import com.hyunec.cosmicbaseballinit.roundGame.domain.Round;
import com.hyunec.cosmicbaseballinit.roundGame.domain.repository.BaseRepository;
import com.hyunec.cosmicbaseballinit.roundGame.domain.repository.RoundRepository;
import com.hyunec.cosmicbaseballinit.roundGame.persistence.dto.BaseDto;
import com.hyunec.cosmicbaseballinit.roundGame.persistence.dto.OutAndScoreDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class BaseService {

    private final BaseRepository baseRepository;
    private final RoundRepository roundRepository;

    /**
     * 진루
     * @return 홈베이스에 들어온 인원 반환
     */
    public Integer advancingBase() {
        BaseDto baseDto = baseRepository.getBases();

        // Base로 변환
        Base bases = new Base(baseDto.getBases());

        // 진루 로직
        Base baseAfterAdvance = bases.advancingBase();
        Integer countOfHitterInHomeBase = baseAfterAdvance.getHitterCountInHomeBase();  // 홈베이스에 들어온 타자 수

        // 홈베이스에 들어온 인원 제거
        Base baseAfterScored = baseAfterAdvance.removeHitterInHomeBase(countOfHitterInHomeBase);

        // update
        baseRepository.updateBase(new BaseDto(baseAfterScored.get()));

        return countOfHitterInHomeBase;
    }

    /**
     * 베이스 조회
     * @return Base: 현재 베이스 상황
     */
    public Base getBases() {
        BaseDto baseDto = baseRepository.getBases();
        return new Base(baseDto.getBases());
    }

    /**
     * 득점
     * @param score : 득점 처리할 점수
     * @deprecated : RoundGameService로 득점 로직 이동으로 삭제 예정
     */
    public void plusScore(Integer score) {
        OutAndScoreDto roundDto = roundRepository.getRoundScore();

        // Round로 변환
        Round round = new Round(roundDto.getOutCount(), roundDto.getScoreCount());

        // 득점 로직
        Round roundAfterGotScored = round.score(score);

        // update
        OutAndScoreDto roundDtoAfterGotScored = new OutAndScoreDto(
                roundAfterGotScored.getOutCount(),
                roundAfterGotScored.getScoreCount());
        roundRepository.updateRoundScore(roundDtoAfterGotScored);
    }

    /**
     * 홈런
     * @deprecated 득점 로직의 RoundGameService 이동으로 삭제 예정
     */
    public void homerun() {
        BaseDto bases = baseRepository.getBases();
        
        // Base로 변환
        Base base = new Base(bases.getBases());
        Integer countOfHitterInBase = base.size(); // 베이스에 나가 있는 총 인원 수
        // 득점
        plusScore(countOfHitterInBase + 1);
        // Bases 초기화
        baseRepository.initBases();
    }

    public void init() {
        baseRepository.initBases();
    }
}
