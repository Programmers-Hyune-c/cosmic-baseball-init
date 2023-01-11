package com.hyunec.cosmicbaseballinit.repository;

import com.hyunec.cosmicbaseballinit.vo.hitterGame.HitterResult;
import com.hyunec.cosmicbaseballinit.vo.hitterGame.PitchResult;

import java.util.List;

public interface HitterGameRepository {

    List<PitchResult> getPitchResultList();
    void savePitchResult(PitchResult pitchResult);
    Integer getCountByPitchResult(PitchResult pitchResult);
    void init();
    // 최종 hitterGame 결과 저장
    void saveHitterGameResult(HitterResult hitterResult);
    Boolean isHitterGameEnd();

    HitterResult getHitterResult();
}
