package com.hyunec.cosmicbaseballinit.service.lv1;

import com.hyunec.cosmicbaseballinit.vo.PitchResult;
import com.hyunec.cosmicbaseballinit.vo.ProbabilityType;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Slf4j
public class Lv1HitterGame {
    private Double strikeProbability;
    private Double ballProbability;
    private Double hitProbability;

    public Lv1HitterGame(ProbabilityType probabilityType) {
        if(probabilityType == ProbabilityType.SAME) {
            Double sameProbability =  this.calculateSameProbability();
            this.strikeProbability = sameProbability;
            this.ballProbability = sameProbability;
            this.hitProbability = sameProbability;
        }
    }

    public Lv1HitterGame(Double strikeProbability, Double ballProbability, Double hitProbability) throws Exception{
        probabilityValidCheck(strikeProbability, ballProbability, hitProbability);

        this.strikeProbability = strikeProbability;
        this.ballProbability = ballProbability;
        this.hitProbability = hitProbability;
    }

    // 확률의 합이 1 인지 체크하는 함수
    private void probabilityValidCheck(Double strikeProbability,
                                       Double ballProbability,
                                       Double hitProbability) throws Exception{
        Double sumValue = 0D;
        sumValue += strikeProbability;
        sumValue += ballProbability;
        sumValue += hitProbability;

        if (!sumValue.equals(Double.valueOf(1))) {
            throw new Exception("확률의 합은 1 이어야 합니다.");
        }
    }

    // 동일한 확률을 계산하는 함수
    private Double calculateSameProbability() {
        Double entireNumber = 1.0;
        Double numberOfHitterResult = (double) PitchResult.values().length;
        return entireNumber / numberOfHitterResult;
    }

}
