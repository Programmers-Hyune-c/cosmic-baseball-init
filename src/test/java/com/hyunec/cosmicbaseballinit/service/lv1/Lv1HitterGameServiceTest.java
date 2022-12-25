package com.hyunec.cosmicbaseballinit.service.lv1;

import com.hyunec.cosmicbaseballinit.vo.HitterResult;
import com.hyunec.cosmicbaseballinit.vo.PitchResult;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.Method;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.DynamicTest.*;

@SpringBootTest
public class Lv1HitterGameServiceTest {
    @Autowired
    Lv1HitterGameService lv1HitterGameService;

    @DisplayName("Lv1 일반야구 게임 세팅, Strike_out")
    @TestFactory
    Stream<DynamicTest> lv1GeneralGameSettingToStrikeOut(){
        return Stream.of(
                dynamicTest("동일 확률로 PitchResult 반환하도록 게임 세팅", () -> {
                    // given

                    // when
                    lv1HitterGameService.setHitGameProbability();
                    Double totalProbability = 0D;

                    // then
                    assertThat(lv1HitterGameService.getProbabilityMap().size())
                            .isEqualTo(PitchResult.values().length);

                    for (PitchResult key : lv1HitterGameService.getProbabilityMap().keySet()) {
                        totalProbability += lv1HitterGameService.getProbabilityMap().get(key);
                    }
                    assertThat(totalProbability).isEqualTo(1);
                }),

                dynamicTest("PitchResult Strike 3개 일시, STRIKE_OUT 반환", () ->{
                    // given
                    PitchResult result1 = PitchResult.STRIKE;
                    PitchResult result2 = PitchResult.STRIKE;
                    PitchResult result3 = PitchResult.STRIKE;

                    // when
                    Method method1 = lv1HitterGameService.getClass().getDeclaredMethod("savePitchResult"
                            , PitchResult.class);
                    method1.setAccessible(true);
                    Method returnHittingResult = lv1HitterGameService.getClass().getDeclaredMethod("returnHittingResult"
                            , PitchResult.class);
                    returnHittingResult.setAccessible(true);

                    method1.invoke(lv1HitterGameService, result1);
                    method1.invoke(lv1HitterGameService, result2);
                    method1.invoke(lv1HitterGameService, result3);

                    // then
                    HitterResult hitterResult = (HitterResult)returnHittingResult.invoke(lv1HitterGameService, result3);
                    Assertions.assertThat(hitterResult.name()).isEqualTo(HitterResult.STRIKE_OUT.name());
                })
        );
    }

    @DisplayName("Lv1 일반야구 게임 세팅, FourBall")
    @TestFactory
    Stream<DynamicTest> lv1GeneralGameSettingToFourBall(){
        return Stream.of(
            dynamicTest("동일 확률로 PitchResult 반환하도록 게임 세팅", () -> {
                // given

                // when
                lv1HitterGameService.setHitGameProbability();
                Double totalProbability = 0D;

                // then
                assertThat(lv1HitterGameService.getProbabilityMap().size())
                        .isEqualTo(PitchResult.values().length);

                for (PitchResult key : lv1HitterGameService.getProbabilityMap().keySet()) {
                    totalProbability += lv1HitterGameService.getProbabilityMap().get(key);
                }
                assertThat(totalProbability).isEqualTo(1);
            }),
            dynamicTest("PitchResult Ball 4개 일시, FOUR_BALL 반환", () ->{
                // given
                PitchResult result1 = PitchResult.BALL;
                PitchResult result2 = PitchResult.BALL;
                PitchResult result3 = PitchResult.BALL;
                PitchResult result4 = PitchResult.BALL;

                // when
                Method method1 = lv1HitterGameService.getClass().getDeclaredMethod("savePitchResult"
                        , PitchResult.class);
                method1.setAccessible(true);
                Method returnHittingResult = lv1HitterGameService.getClass().getDeclaredMethod("returnHittingResult"
                        , PitchResult.class);
                returnHittingResult.setAccessible(true);

                method1.invoke(lv1HitterGameService, result1);
                method1.invoke(lv1HitterGameService, result2);
                method1.invoke(lv1HitterGameService, result3);
                method1.invoke(lv1HitterGameService, result4);

                // then
                HitterResult hitterResult = (HitterResult) returnHittingResult.invoke(lv1HitterGameService, result4);
                Assertions.assertThat(hitterResult.name()).isEqualTo(HitterResult.FOUR_BALL.name());
                lv1HitterGameService.initGameScore();
            })
        );
    }
}
