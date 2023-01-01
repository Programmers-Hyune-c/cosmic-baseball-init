package com.hyunec.cosmicbaseballinit.service.lv1;

import com.hyunec.cosmicbaseballinit.vo.HitterResult;
import com.hyunec.cosmicbaseballinit.vo.PitchResult;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.util.*;

@Getter
@Service
public class HitterGameService {
    // TODO: 1급 컬렉션 참고하기
    public final Map<PitchResult, Double> probabilityMap = new HashMap<>(); 
    public final List<PitchResult> hittingResult = new ArrayList<>();

    // 확률 세팅
    public void setHitGameProbability(){
        PitchResult.settingProbability(probabilityMap);
    }

    public String hitting() throws Exception {
        PitchResult pitchResult = PitchResult.pitching(probabilityMap, Math.random());
        savePitchResult(pitchResult);
        Optional<HitterResult> hitterResult = returnHittingResult(pitchResult);
        return hitterResult.map(Enum::name).orElseGet(pitchResult::name);
    }

    private void savePitchResult(PitchResult pitchResult) {
        hittingResult.add(pitchResult);
    }

    private Optional<HitterResult> returnHittingResult(PitchResult pitchResult) throws Exception {
        return HitterResult.judgeHitterResultByPitchResult(pitchResult, getCountByPitchResult(pitchResult));
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

    //TODO : hitterResult에 맞게 수정하기
//    public boolean isHitterGameEnd(String hittingResult){
//        for (HitterResult hr : HitterResult.values()){
//            if (hr.name().equals(hittingResult)){
//                return true;
//            }
//        }
//        for (SpecialHitterResult shr : SpecialHitterResult.values()){
//            if (shr.name().equals(hittingResult)){
//                return true;
//            }
//        }
//        return false;
//    }
}
