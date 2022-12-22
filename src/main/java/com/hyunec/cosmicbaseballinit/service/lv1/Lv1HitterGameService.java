package com.hyunec.cosmicbaseballinit.service.lv1;

import com.hyunec.cosmicbaseballinit.vo.HitterResult;
import com.hyunec.cosmicbaseballinit.vo.PitchResult;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

@Getter
@Service
public class Lv1HitterGameService {
    // TODO: 1급 컬렉션 참고하기
    public final Map<PitchResult, Double> probabilityMap = new HashMap<>(); 
    public final List<PitchResult> hittingResult = new ArrayList<>();

    // 확률 세팅
    public void setHitGameProbability(){
        PitchResult.settingProbability(probabilityMap);
    }

    public String hitting() throws Exception{
        PitchResult pitchResult = PitchResult.pitching(probabilityMap);
        savePitchResultToMap(pitchResult);
        return returnHittingResult(pitchResult);
    }

    private void savePitchResultToMap(PitchResult pitchResult) {
        hittingResult.add(pitchResult);
    }

    private String returnHittingResult(PitchResult pitchResult) throws Exception {
        if (getCountByPitchResult(pitchResult) == pitchResult.getValue()){ // S3,B4,H1
            initGameScore();
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
