package com.hyunec.cosmicbaseballinit.dao;

import com.hyunec.cosmicbaseballinit.repository.HitterGameRepository;
import com.hyunec.cosmicbaseballinit.vo.hitterGame.HitterResult;
import com.hyunec.cosmicbaseballinit.vo.hitterGame.PitchResult;
import lombok.Getter;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class HitterGameDao implements HitterGameRepository {

    // 투구 결과
    private List<PitchResult> pitchResultList = new ArrayList<>();
    // 타구 게임 결과
    private Optional<HitterResult> hitterResult = Optional.empty();

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
        hitterResult = Optional.empty();
    }

    @Override
    public void saveHitterGameResult(HitterResult hitterResult) {
        this.hitterResult = Optional.of(hitterResult);
    }

    @Override
    public Boolean isHitterGameEnd() {
        return hitterResult.isPresent();
    }

    @Override
    public HitterResult getHitterResult() {
        return hitterResult.get();
    }
}
