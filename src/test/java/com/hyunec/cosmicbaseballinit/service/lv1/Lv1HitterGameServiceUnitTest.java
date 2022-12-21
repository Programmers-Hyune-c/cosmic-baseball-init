package com.hyunec.cosmicbaseballinit.service.lv1;

import com.hyunec.cosmicbaseballinit.vo.HitterResult;
import com.hyunec.cosmicbaseballinit.vo.PitchResult;
import com.hyunec.cosmicbaseballinit.vo.SpecialHitterResult;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.Method;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class Lv1HitterGameServiceUnitTest {
    @Autowired
    Lv1HitterGameService lv1HitterGameService;

    @Test
    @DisplayName("SpecialHitterResult 일 때 점수 초기화 true를 반환하는지")
    void isWhenScoreInitTestBullseyeBall(){
        // given
        String specialHitterResult = SpecialHitterResult.BULLSEYE_BALL.name();

        // when
        Boolean result = lv1HitterGameService.isWhenScoreInit(specialHitterResult);

        // then
        assertThat(result).isEqualTo(true);
        lv1HitterGameService.initGameScore();
    }

    @Test
    @DisplayName("2스트라이크 일 때 점수 초기화 false를 반환하는지")
    void isWhenScoreInitTestFalse() throws Exception{
        // given
        PitchResult result1 = PitchResult.STRIKE;
        Method method1 = lv1HitterGameService.getClass().getDeclaredMethod("savePitchResult"
                , PitchResult.class);
        method1.setAccessible(true);
        method1.invoke(lv1HitterGameService, result1);

        // when
        PitchResult result2 = PitchResult.STRIKE;
        method1.invoke(lv1HitterGameService, result2);
        Boolean result = lv1HitterGameService.isWhenScoreInit(result2.name());

        // then
        assertThat(result).isEqualTo(false);
    }
}
