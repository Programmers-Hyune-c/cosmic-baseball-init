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

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.DynamicTest.*;

@SpringBootTest
public class Lv1HitterGameServiceTest {
    @Autowired
    Lv1HitterGameService lv1HitterGameService;

    @DisplayName("Lv1 일반야구 게임 세팅, 투구 결과에 따른 타자의 결과 반환")
    @TestFactory
    Stream<DynamicTest> lv1GeneralGameSettingToReturnHitterResult(){
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
                    Method savePitchResultToMap = lv1HitterGameService.getClass().getDeclaredMethod("savePitchResultToMap"
                            , PitchResult.class);
                    savePitchResultToMap.setAccessible(true);
                    Method returnHittingResult = lv1HitterGameService.getClass().getDeclaredMethod("returnHittingResult"
                            , PitchResult.class);
                    returnHittingResult.setAccessible(true);

                    savePitchResultToMap.invoke(lv1HitterGameService, result1);
                    savePitchResultToMap.invoke(lv1HitterGameService, result2);
                    savePitchResultToMap.invoke(lv1HitterGameService, result3);

                    // then
                    String hitterResult = (String) returnHittingResult.invoke(lv1HitterGameService, result3);
                    Assertions.assertThat(hitterResult).isEqualTo(HitterResult.STRIKE_OUT.name());
                }),
                dynamicTest("PitchResult Ball 4개 일시, FOUR_BALL 반환", () ->{
                    // given
                    PitchResult result1 = PitchResult.BALL;
                    PitchResult result2 = PitchResult.BALL;
                    PitchResult result3 = PitchResult.BALL;
                    PitchResult result4 = PitchResult.BALL;

                    // when
                    Method savePitchResultToMap = lv1HitterGameService.getClass().getDeclaredMethod("savePitchResultToMap"
                            , PitchResult.class);
                    savePitchResultToMap.setAccessible(true);
                    Method returnHittingResult = lv1HitterGameService.getClass().getDeclaredMethod("returnHittingResult"
                            , PitchResult.class);
                    returnHittingResult.setAccessible(true);

                    savePitchResultToMap.invoke(lv1HitterGameService, result1);
                    savePitchResultToMap.invoke(lv1HitterGameService, result2);
                    savePitchResultToMap.invoke(lv1HitterGameService, result3);
                    savePitchResultToMap.invoke(lv1HitterGameService, result4);

                    // then
                    String hitterResult = (String) returnHittingResult.invoke(lv1HitterGameService, result4);
                    Assertions.assertThat(hitterResult).isEqualTo(HitterResult.FOUR_BALL.name());
                })
        );
    }
}