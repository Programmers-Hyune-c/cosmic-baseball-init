package com.hyunec.cosmicbaseballinit.roundGame.persistence.dao;

import com.hyunec.cosmicbaseballinit.roundGame.domain.Base;
import com.hyunec.cosmicbaseballinit.roundGame.domain.repository.BaseRepository;
import com.hyunec.cosmicbaseballinit.roundGame.persistence.dto.BaseDto;
import org.springframework.stereotype.Repository;

import java.util.LinkedList;
import java.util.Queue;

@Repository
public class BaseDao implements BaseRepository {

    Queue<Integer> bases = new LinkedList<>();

    @Override
    public BaseDto getBases() {
        return new BaseDto(bases);
    }

    @Override
    public void updateBase(BaseDto baseDto) {
        bases = baseDto.getBases();
    }

    @Override
    public void initBases() {
        bases.clear();
    }
}
