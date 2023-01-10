package com.hyunec.cosmicbaseballinit.roundGame.domain.service;

import com.hyunec.cosmicbaseballinit.roundGame.domain.Base;
import com.hyunec.cosmicbaseballinit.roundGame.domain.Round;
import com.hyunec.cosmicbaseballinit.roundGame.domain.repository.BaseRepository;
import com.hyunec.cosmicbaseballinit.roundGame.domain.repository.RoundRepository;
import com.hyunec.cosmicbaseballinit.roundGame.persistence.dto.BaseDto;
import com.hyunec.cosmicbaseballinit.roundGame.persistence.dto.OutAndScoreDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Queue;

@RequiredArgsConstructor
@Service
public class BaseService {

    private final BaseRepository baseRepository;
    private final RoundRepository roundRepository;

    /**
     * 진루
     */
    public void advancingBase() {
        BaseDto baseDto = baseRepository.getBases();

        // Base로 변환
        Base bases = new Base(baseDto.getBases());

        // 진루 로직
        Base baseAfterAdvance = bases.advancingBase();
        Integer countOfHitterInHomeBase = baseAfterAdvance.getHitterCountInHomeBase();  // 홈베이스에 들어온 타자 수

        // countOfHitterInHomeBase 만큼 득점 처리
        plusScore(countOfHitterInHomeBase);
        // 홈베이스에 들어온 인원 제거
        Base baseAfterScored = baseAfterAdvance.removeHitterInHomeBase(countOfHitterInHomeBase);

        // update
        baseRepository.updateBase(new BaseDto(baseAfterScored.get()));
    }

    /**
     * 득점
     * @param score : 득점 처리할 점수
     */
    public void plusScore(Integer score) {
        OutAndScoreDto roundDto = roundRepository.getRoundScore();

        // Round로 변환
        Round round = new Round(roundDto.getOutCount(), roundDto.getScoreCount());

        // 득점 로직
        Round roundAfterGotScored = round.plusScore(score);

        // update
        OutAndScoreDto roundDtoAfterGotScored = new OutAndScoreDto(
                roundAfterGotScored.getOutCount(),
                roundAfterGotScored.getScoreCount());
        roundRepository.updateRoundScore(roundDtoAfterGotScored);
    }

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
