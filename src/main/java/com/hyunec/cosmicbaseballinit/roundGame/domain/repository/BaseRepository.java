package com.hyunec.cosmicbaseballinit.roundGame.domain.repository;

import com.hyunec.cosmicbaseballinit.roundGame.domain.Base;
import com.hyunec.cosmicbaseballinit.roundGame.persistence.dto.BaseDto;

public interface BaseRepository {
    public BaseDto getBases();
    public void updateBase(BaseDto baseDto);
    public void deleteBases(BaseDto baseDto);
}
