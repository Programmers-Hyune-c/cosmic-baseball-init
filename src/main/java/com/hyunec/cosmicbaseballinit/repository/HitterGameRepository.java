package com.hyunec.cosmicbaseballinit.repository;

import com.hyunec.cosmicbaseballinit.vo.hitterGame.PitchResult;

import java.util.List;

public interface HitterGameRepository {

    List<PitchResult> getPitchResultList();
    void savePitchResult(PitchResult pitchResult);
    Integer getCountByPitchResult(PitchResult pitchResult);
    void init();
}
