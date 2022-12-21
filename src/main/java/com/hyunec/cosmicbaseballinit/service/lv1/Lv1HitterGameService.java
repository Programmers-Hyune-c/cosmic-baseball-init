package com.hyunec.cosmicbaseballinit.service.lv1;

import com.hyunec.cosmicbaseballinit.vo.HitterResult;
import com.hyunec.cosmicbaseballinit.vo.PitchResult;
import com.hyunec.cosmicbaseballinit.vo.SpecialHitterResult;
import lombok.Getter;
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

    public String hitting() throws Exception {
        PitchResult pitchResult = PitchResult.pitching(probabilityMap, Math.random());
        SpecialHitterResult specialHitterResult =
                SpecialHitterResult.judgeSpecialHitterResultByPitchResult(pitchResult, Math.random());
        if (specialHitterResult != null){
            return specialHitterResult.name();
        }
        savePitchResult(pitchResult);
        return returnHittingResult(pitchResult);
    }

    private void savePitchResult(PitchResult pitchResult) {
        hittingResult.add(pitchResult);
    }

    private String returnHittingResult(PitchResult pitchResult) throws Exception {

        if (getCountByPitchResult(pitchResult) == pitchResult.getValue()){ // S3,B4,H1
            return HitterResult.judgeHitterResultByPitchResult(pitchResult).name();
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

    public void initGameScore() {
        hittingResult.clear();
    }

    public boolean isWhenScoreInit(String hittingResult){
        for (HitterResult hr : HitterResult.values()){
            if (hr.name().equals(hittingResult)){
                return true;
            }
        }
        for (SpecialHitterResult shr : SpecialHitterResult.values()){
            if (shr.name().equals(hittingResult)){
                return true;
            }
        }
        return false;
    }
}
