package com.hyunec.cosmicbaseballinit.dao;

import com.hyunec.cosmicbaseballinit.repository.HitterGameRepository;
import com.hyunec.cosmicbaseballinit.vo.hitterGame.PitchResult;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class HitterGameDao implements HitterGameRepository {

    // Strike, Ball, out 확률
    private HitterGameProbabilitiesDao pitchProbabilities;
    // 투구 결과
    public List<PitchResult> pitchResultList = new ArrayList<>();

    @Override
    public List<PitchResult> getPitchResultList() {
        return pitchResultList;
    }

    @Override
    public void savePitchResult(PitchResult pitchResult) {
        pitchResultList.add(pitchResult);
    }

    @Override
    public Integer getCountByPitchResult(PitchResult pitchResult) {
        return (int)pitchResultList.stream().filter(x -> x.equals(pitchResult)).count();
    }

    @Override
    public void init() {
        pitchResultList.clear();
    }
}
