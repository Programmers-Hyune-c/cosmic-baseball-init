package com.hyunec.cosmicbaseballinit.service.lv1;

import com.hyunec.cosmicbaseballinit.vo.HitterResult;
import com.hyunec.cosmicbaseballinit.vo.PitchResult;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

@Getter
public class Lv1HitterGameService {
    public final Map<PitchResult, Double> probabilityMap = new HashMap<>(); // TODO: 1급 컬렉션
    public final List<PitchResult> hittingResult = new ArrayList<>();

    // 확률 세팅
    public void setHitGameProbability(){
        PitchResult.settingProbability(probabilityMap);
    }

    public String hitting() throws Exception{
        PitchResult pitchResult = PitchResult.pitching(probabilityMap);
        hittingResult.add(pitchResult);
        if (getCountByPitchResult(pitchResult) == pitchResult.getValue()){
            return HitterResult.getHitterResultByPitchResult(pitchResult).name();
        }
        return pitchResult.name();
    }

    public Map<PitchResult, Integer> getScores() {
        Map<PitchResult, Integer> scores = new HashMap<>();
        Arrays.stream(PitchResult.values()).forEach(pr -> scores.put(pr, getCountByPitchResult(pr)));
        return scores;
    }

    private Integer getCountByPitchResult(PitchResult pitchResult) {
        return (int)hittingResult.stream().filter(x -> x.equals(pitchResult)).count();
    }

    private void initGameScore() {
        hittingResult.clear();
    }
}
