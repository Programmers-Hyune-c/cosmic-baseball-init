package com.hyunec.cosmicbaseballinit.service.lv1;

import com.hyunec.cosmicbaseballinit.vo.HitterResult;
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
    private PitchResult pitchResult;
    private Integer count;
    private HitterResult hitterResult;

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

    // 한 타석의 게임 시작
    public void oneHitterGameStart() throws Exception{

        Integer numberOfStike = 0;
        Integer numberOfball = 0;
        Integer numberOfhit = 0;
        while(!isHitterEnd(numberOfStike, numberOfball, numberOfhit)) {
            // TODO : 타구 확률에 따라 스트라이크, 볼, 히트 수 증가 함수 만들기
        }

        // 피칭 결과 필드 변수에 저장
        setPitchResultAndCount(numberOfStike, numberOfball, numberOfhit);

        // 피칭 결과로 타석의 결과 판단
        hitterResult = judgeHitResult(pitchResult, count);

        // 한 타석의 결과 로그 찍기
        log.info("-------- One Hitter Game End ----------");
        log.info("------ result -----");
        log.info("HitterResult : {}", hitterResult.name());
        log.info("HitterResult : {}", pitchResult.name());
        log.info("HitterResult : {}", count.toString());
    }

    /* 타석이 끝나는 규칙
     * strike 3 -> end
     * ball 4 -> end
     * hit 1 -> end
     * */
    private Boolean isHitterEnd(Integer numberOfStike, Integer numberOfball, Integer numberOfHit) {
        if(numberOfStike.intValue() >= 3 || numberOfball.intValue() >= 4 || numberOfHit >= 1) {
            return true;
        }
        return false;
    }

    // 피칭 결과를 필드 변수에 세팅
    private void setPitchResultAndCount(Integer numberOfStike,
                                        Integer numberOfball,
                                        Integer numberOfhit) throws Exception{
        if(numberOfStike >= 3) {
            pitchResult = PitchResult.STRIKE;
            count = 3;
        }
        if(numberOfball >= 4) {
            pitchResult = PitchResult.BALL;
            count = 4;
        }
        if(numberOfhit >= 1) {
            pitchResult = PitchResult.HIT;
            count = 1;
        }
        throw new Exception("returnPitchResult 오류 입니다.");
    }

    // 한 타석의 결과 판단
    private HitterResult judgeHitResult(PitchResult pitchResult, Integer count) throws Exception{

        if(pitchResult == PitchResult.STRIKE && count.equals(3)) { // 스트라이크 3
            return HitterResult.OUT;
        }

        if((pitchResult == PitchResult.BALL && count.equals(4))
                || (pitchResult == PitchResult.HIT && count.equals(1))) { // 볼4, 타구
            return HitterResult.GO;
        }

        throw new Exception("judgeHitResult 에러입니다.");
    }
}
