package com.hyunec.cosmicbaseballinit.service.lv1;

import com.hyunec.cosmicbaseballinit.repository.HitterGameProbabilitiesRepository;
import com.hyunec.cosmicbaseballinit.repository.HitterGameRepository;
import com.hyunec.cosmicbaseballinit.vo.hitterGame.HitterResult;
import com.hyunec.cosmicbaseballinit.vo.hitterGame.PitchProbabilitySettingVo;
import com.hyunec.cosmicbaseballinit.vo.hitterGame.PitchResult;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Getter
@Service
@RequiredArgsConstructor
public class HitterGameService {
    //private HitterGameProbabilities pitchProbabilities;
    private final HitterGameRepository hitterGameRepository;
    private final HitterGameProbabilitiesRepository probabilitiesRepository;

    public void setHitGameProbability() {
        probabilitiesRepository.save();
    }

    public void setHitGameProbability(PitchProbabilitySettingVo pitchProbabilitySettingVo) {
        probabilitiesRepository.save(pitchProbabilitySettingVo);
    }

    // 타구
    public String hitting(Double pitchResultRandomDouble, Double hitterResultRandomDouble) throws Exception {
        PitchResult pitchResult = PitchResult.pitching(probabilitiesRepository.get(), pitchResultRandomDouble);
        savePitchResult(pitchResult);
        Optional<HitterResult> hitterResult = returnHittingResult(pitchResult, hitterResultRandomDouble);
        if (hitterResult.isPresent()){
            // 결과 저장
            hitterGameRepository.saveHitterGameResult(hitterResult.get());
            return hitterResult.get().name();
        }
        return pitchResult.name();
    }

    // 투구 결과 저장
    private void savePitchResult(PitchResult pitchResult) {
        hitterGameRepository.savePitchResult(pitchResult);
    }

    private Optional<HitterResult> returnHittingResult(PitchResult pitchResult, Double randomDouble) throws Exception {
        return HitterResult.judgeHitterResultByPitchResult(
                pitchResult, hitterGameRepository.getCountByPitchResult(pitchResult), randomDouble);
    }

    public Map<PitchResult, Integer> getScores() {
        Map<PitchResult, Integer> scores = new HashMap<>();
        Arrays.stream(PitchResult.values()).forEach(pr -> scores.put(pr
                , hitterGameRepository.getCountByPitchResult(pr)));
        return scores;
    }

    public void initGameScore() {
        hitterGameRepository.init();
    }

    public Boolean isHitterGameEnd() {
        return hitterGameRepository.isHitterGameEnd();
    }

    public HitterResult getHitterResult() {
        return hitterGameRepository.getHitterResult();
    }
}
