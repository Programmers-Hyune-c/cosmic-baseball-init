package com.hyunec.cosmicbaseballinit.service.lv1;

import com.hyunec.cosmicbaseballinit.vo.HitterResult;
import com.hyunec.cosmicbaseballinit.vo.PitchResult;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.DynamicTest.*;

@SpringBootTest
public class HitterGameServiceTest {
    @Autowired
    HitterGameService hitterGameService;

    @BeforeEach
    public void InitHitterScore() {
        hitterGameService.initGameScore();
    }

    @DisplayName("Lv1 일반야구 게임 세팅, Strike_out 반환")
    @TestFactory
    Stream<DynamicTest> lv1GeneralGameSettingToStrikeOut() {
        return Stream.of(
                dynamicTest("동일 확률로 PitchResult 반환하도록 게임 세팅", () -> {
                    // given

                    // when
                    hitterGameService.setHitGameProbability();
                    Double totalProbability = 0D;

                    // then
                    assertThat(hitterGameService.getProbabilityMap().size())
                            .isEqualTo(PitchResult.values().length);

                    for (PitchResult key : hitterGameService.getProbabilityMap().keySet()) {
                        totalProbability += hitterGameService.getProbabilityMap().get(key);
                    }
                    assertThat(totalProbability).isEqualTo(1);
                }),

                dynamicTest("STRIKE_OUT 반환", () ->{
                    // given
                    Double pitchResultRandomDouble = 0.2;
                    Double hitterResultRandomDouble = 0.4;
                    hitterGameService.hitting(pitchResultRandomDouble, hitterResultRandomDouble);
                    hitterGameService.hitting(pitchResultRandomDouble, hitterResultRandomDouble);

                    // when
                    String hittingResult = hitterGameService.hitting(pitchResultRandomDouble, hitterResultRandomDouble);
                    Map<PitchResult, Integer> scores = hitterGameService.getScores();

                    // then
                    assertThat(hittingResult).isEqualTo(HitterResult.STRIKE_OUT.name());
                    assertThat(scores.get(PitchResult.STRIKE)).isEqualTo(3);
                    System.out.println(scores);
                })
        );
    }

    @DisplayName("Lv1 일반야구 게임 세팅, Homerun 반환")
    @TestFactory
    Stream<DynamicTest> lv1GeneralGameSettingToHomerun(){
        return Stream.of(
            dynamicTest("동일 확률로 PitchResult 반환하도록 게임 세팅", () -> {
                // given

                // when
                hitterGameService.setHitGameProbability();
                Double totalProbability = 0D;

                // then
                assertThat(hitterGameService.getProbabilityMap().size())
                        .isEqualTo(PitchResult.values().length);

                for (PitchResult key : hitterGameService.getProbabilityMap().keySet()) {
                    totalProbability += hitterGameService.getProbabilityMap().get(key);
                }
                assertThat(totalProbability).isEqualTo(1);
            }),
            dynamicTest("1스트라이크, 2볼, 이후 homerun 반환", () ->{
                // given
                Double strikePitchDouble = 0.2;
                Double strikehitterDouble = 0.4;

                Double ballPitchDouble = 0.4;
                Double ballhitterDouble = 0.4;

                Double homerunPitchDouble = 0.7;
                Double homerunhitterDouble = 0.19;

                // when
                // 1 Strike
                hitterGameService.hitting(strikePitchDouble, strikehitterDouble);

                // 2 Ball
                hitterGameService.hitting(ballPitchDouble, ballhitterDouble);
                hitterGameService.hitting(ballPitchDouble, ballhitterDouble);

                // Homerun
                String hittingResult = hitterGameService.hitting(homerunPitchDouble, homerunhitterDouble);
                Map<PitchResult, Integer> scores = hitterGameService.getScores();

                // then
                assertThat(hittingResult).isEqualTo(HitterResult.HOMERUN.name());
                assertThat(scores.get(PitchResult.STRIKE)).isEqualTo(1);
                assertThat(scores.get(PitchResult.BALL)).isEqualTo(2);
                System.out.println(scores);
            })
        );
    }

    @DisplayName("Lv1 일반야구 게임 세팅, FourBall")
    @TestFactory
    Stream<DynamicTest> lv1GeneralGameSettingToFourBall() {
        return Stream.of(
                dynamicTest("동일 확률로 PitchResult 반환하도록 게임 세팅", () -> {
                    // given

                    // when
                    hitterGameService.setHitGameProbability();
                    Double totalProbability = 0D;

                    // then
                    assertThat(hitterGameService.getProbabilityMap().size())
                            .isEqualTo(PitchResult.values().length);

                    for (PitchResult key : hitterGameService.getProbabilityMap().keySet()) {
                        totalProbability += hitterGameService.getProbabilityMap().get(key);
                    }
                    assertThat(totalProbability).isEqualTo(1);
                }),
                dynamicTest("PitchResult Ball 4개 일시, FOUR_BALL 반환", () -> {
                    // given
                    Double pitchResultRandomDouble = 0.4;
                    Double hitterResultRandomDouble = 0.4;

                    // when
                    hitterGameService.hitting(pitchResultRandomDouble, hitterResultRandomDouble);
                    hitterGameService.hitting(pitchResultRandomDouble, hitterResultRandomDouble);
                    hitterGameService.hitting(pitchResultRandomDouble, hitterResultRandomDouble);
                    String hittingResult = hitterGameService.hitting(pitchResultRandomDouble, hitterResultRandomDouble);
                    Map<PitchResult, Integer> scores = hitterGameService.getScores();

                    // then
                    assertThat(hittingResult).isEqualTo(HitterResult.FOUR_BALL.name());
                    assertThat(scores.get(PitchResult.BALL)).isEqualTo(4);
                    System.out.println(scores);
                })
        );
    }
}
