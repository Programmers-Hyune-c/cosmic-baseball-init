package com.hyunec.cosmicbaseballinit.service.lv1;

import com.hyunec.cosmicbaseballinit.vo.HitterResult;
import com.hyunec.cosmicbaseballinit.vo.PitchResult;
import com.hyunec.cosmicbaseballinit.vo.ProbabilityType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;

public class Lv1HitterGameTest {

    Lv1HitterGame lv1HitterGame = new Lv1HitterGame(ProbabilityType.SAME);

    @Test
    @DisplayName("생성자 유효성 검사 테스트")
    public void  constructorValidationCheckTest() throws Exception {
        // given
        Double strikeProbability = 0.2;
        Double ballProbability = 0.2;
        Double hitProbability = 0.2;

        // then
        assertThatThrownBy(() -> {new Lv1HitterGame(strikeProbability, ballProbability, hitProbability);
        }).isInstanceOf(Exception.class)
                .hasMessage("확률의 합은 1 이어야 합니다.");
    }

    @Test
    @DisplayName("Strike, ball, hit 확률을 동일한 확률로 HitterGame 생성자를 만드는지 테스트")
    public void  lv1HitterGameConstructorSameProbability() throws Exception {
        // given
        Lv1HitterGame lv1HitterGame = new Lv1HitterGame(ProbabilityType.SAME);

        // when
        Double strikeProbability = lv1HitterGame.getStrikeProbability();
        Double ballProbability = lv1HitterGame.getBallProbability();
        Double hitProbability = lv1HitterGame.getHitProbability();

        // then
        assertThat(strikeProbability).isEqualTo(ballProbability).isEqualTo(hitProbability).isEqualTo(1.0/3.0);
        System.out.println(strikeProbability);
    }
    
    @Test
    @DisplayName("타석이 끝났는지 체크하는 함수 테스트")
    public void isHitterEndTest () throws Exception {
        // given
        Integer numberOfStike = 3;
        Integer numberOfball = 0;
        Integer numberOfHit = 0;
        
        // private method Test
        Method method = lv1HitterGame.getClass()
                .getDeclaredMethod("isHitterEnd", Integer.class, Integer.class, Integer.class);
        method.setAccessible(true);

        // when
        Boolean result1 = (Boolean)method.invoke(lv1HitterGame, numberOfStike, numberOfball, numberOfHit);
        Boolean result2 = (Boolean)method.invoke(lv1HitterGame, 2, 3, 0);

        // then
        assertThat(result1).isEqualTo(true);
        assertThat(result2).isEqualTo(false);
    }
    
    @Test
    @DisplayName("투구 결과로 타석의 결과를 반환하는 함수 테스트")
    public void  judgeHitResultTest() throws Exception {
        // given
        PitchResult pitchResult = PitchResult.STRIKE;
        Integer count = 3;
        
        // private method
        Method method = lv1HitterGame.getClass()
                .getDeclaredMethod("judgeHitResult", PitchResult.class, Integer.class);
        method.setAccessible(true);

        // when
        HitterResult hitterResultStrikeOut = (HitterResult)method.invoke(lv1HitterGame, pitchResult, count);
        HitterResult hitterResult4Ball = (HitterResult)method.invoke(lv1HitterGame, PitchResult.BALL, 4);
        HitterResult hitterResult1Hit = (HitterResult)method.invoke(lv1HitterGame, PitchResult.HIT, 1);
        //Throwable hitterResultException = catchThrowable(() -> { method.invoke(lv1HitterGame, PitchResult.BALL, 5); });
        
        // then
        assertThat(hitterResultStrikeOut).isEqualTo(HitterResult.OUT);
        assertThat(hitterResult4Ball).isEqualTo(hitterResult1Hit).isEqualTo(HitterResult.GO);
//        assertThat(hitterResultException).isInstanceOf(Exception.class)
//                .hasMessageContaining("judgeHitResult 에러입니다."); // null 반환...
    }
    
    @Test
    @DisplayName("랜덤 값이 주어질 때 확률 구간에 따른 PitchResult 반환")
    public void returnPitchResultAccordingtoProbabilitySectionTest () throws Exception {
        // given
        Double randomNumber = 0.32;

        // private method
        Method method = lv1HitterGame.getClass()
                .getDeclaredMethod("returnPitchResultAccordingtoProbabilitySection", Double.class);
        method.setAccessible(true);
        
        // when
        PitchResult pitchResultStike = (PitchResult)method.invoke(lv1HitterGame, randomNumber);
        PitchResult pitchResultBall = (PitchResult)method.invoke(lv1HitterGame, 0.4);
        PitchResult pitchResultHit = (PitchResult)method.invoke(lv1HitterGame, 0.77);
        
        // then
        assertThat(pitchResultStike).isEqualTo(PitchResult.STRIKE);
        assertThat(pitchResultBall).isEqualTo(PitchResult.BALL);
        assertThat(pitchResultHit).isEqualTo(PitchResult.HIT);
    }
}
