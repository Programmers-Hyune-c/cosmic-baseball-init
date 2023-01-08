package com.hyunec.cosmicbaseballinit.roundGame.domain.service;

import com.hyunec.cosmicbaseballinit.roundGame.domain.Base;
import com.hyunec.cosmicbaseballinit.roundGame.domain.repository.BaseRepository;
import com.hyunec.cosmicbaseballinit.roundGame.persistence.dto.BaseDto;
import lombok.RequiredArgsConstructor;

import java.util.Queue;

@RequiredArgsConstructor
public class BaseService {

    private final BaseRepository baseRepository;

    // 진루
    public void advancingBase() {
        BaseDto baseDto = baseRepository.getBases();

        // Base로 변환
        Base bases = new Base(baseDto.getBases());

        // 진루 로직
        Base baseAfterAdvance = bases.advancingBase();
        Integer countOfHitterInHomeBase = baseAfterAdvance.getHitterCountInHomeBase();  // 홈베이스에 들어온 타자 수
        //TODO: countOfHitterInHomeBase 만큼 득점 처리
        Base baseAfterScored = baseAfterAdvance.removeHitterInHomeBase(countOfHitterInHomeBase); // 홈베이스에 들어온 인원 제거

        // update
        baseRepository.updateBase(new BaseDto(baseAfterScored.get()));
    }

    // 득점
    public void plusScore() {

    }

    //


}
