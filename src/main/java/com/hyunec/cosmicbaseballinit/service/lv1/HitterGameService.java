package com.hyunec.cosmicbaseballinit.service.lv1;

import com.hyunec.cosmicbaseballinit.vo.hitterGame.HitterGameProbabilities;
import com.hyunec.cosmicbaseballinit.vo.hitterGame.PitchProbabilitySettingVo;
import com.hyunec.cosmicbaseballinit.vo.hitterGame.HitterResult;
import com.hyunec.cosmicbaseballinit.vo.hitterGame.PitchResult;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Getter
@Service
@RequiredArgsConstructor
public class HitterGameService {
    // TODO: 1급 컬렉션 참고하기
    private HitterGameProbabilities pitchProbabilities;
    public final List<PitchResult> hittingResult = new ArrayList<>();

    //TODO: 분리 어떻게?
    // 동일 확률로 게임 세팅
    public void setHitGameProbability() {
        Map<PitchResult, Double> probabilityMap = new HashMap<>();
        Double sameProbability = calculateSameProbability();
        Arrays.stream(PitchResult.values()).forEach(pitchResult -> probabilityMap.put(pitchResult, sameProbability));
        pitchProbabilities = new HitterGameProbabilities(probabilityMap);
    }

    // 입력 받은 확률로 게임 세팅
    public void setHitGameProbability(PitchProbabilitySettingVo pitchProbabilitySettingVo) {
        Map<PitchResult, Double> probabilityMap = new HashMap<>();
        probabilityMap.put(PitchResult.STRIKE, pitchProbabilitySettingVo.getStrikeProbabiltiy());
        probabilityMap.put(PitchResult.BALL, pitchProbabilitySettingVo.getBallProbabiltiy());
        probabilityMap.put(PitchResult.HIT, pitchProbabilitySettingVo.getHitProbabiltiy());
        pitchProbabilities = new HitterGameProbabilities(probabilityMap);
    }

    // 동일한 확률을 계산하는 함수
    private static Double calculateSameProbability() {
        Double entireNumber = 1.0;
        Double numberOfHitterResult = (double) PitchResult.values().length;
        return entireNumber / numberOfHitterResult;
    }

    // 타구
    public String hitting(Double pitchResultRandomDouble, Double hitterResultRandomDouble) throws Exception {
        PitchResult pitchResult = PitchResult.pitching(pitchProbabilities.getPitchResultProbabilities(), pitchResultRandomDouble);
        savePitchResult(pitchResult);
        Optional<HitterResult> hitterResult = returnHittingResult(pitchResult, hitterResultRandomDouble);
        return hitterResult.map(Enum::name).orElseGet(pitchResult::name);
    }

    // 투구 결과 저장
    private void savePitchResult(PitchResult pitchResult) {
        hittingResult.add(pitchResult);
    }

    private Optional<HitterResult> returnHittingResult(PitchResult pitchResult, Double randomDouble) throws Exception {
        return HitterResult.judgeHitterResultByPitchResult(
                pitchResult, getCountByPitchResult(pitchResult), randomDouble);
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
