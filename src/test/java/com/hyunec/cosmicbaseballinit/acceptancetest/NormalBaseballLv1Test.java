package com.hyunec.cosmicbaseballinit.acceptancetest;

import static org.assertj.core.api.Assertions.assertThat;

import com.hyunec.cosmicbaseballinit.domain.baseball.model.Batting;
import com.hyunec.cosmicbaseballinit.domain.baseball.model.BattingGenerator;
import com.hyunec.cosmicbaseballinit.domain.baseball.model.RandomBattingGenerator;
import java.util.Random;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class NormalBaseballLv1Test {

  @DisplayName("strike, ball, hit 는 같은 확률 입니다.")
  @ParameterizedTest
  @ValueSource(ints = {0, 1, 2, 3, 4, 5})
  void t1(int number) {
    //given
    int MAX_NUMBER = 3;
    BattingGenerator battingGenerator = new RandomBattingGenerator(createRandom(number));

    //when
    Batting generateValue = battingGenerator.generator();

    //then
    assertThat(generateValue).isEqualTo(Batting.of(number % MAX_NUMBER));
  }

  @DisplayName("3B 타석에서 타격 결과가 ball 이면 타석 결과는 four_ball 됩니다.")
  void t2() {
  }

  @DisplayName("2S 타석에서 타격 결과가 strike 이면 타석 결과는 out 됩니다.")
  void t3() {
  }

  @DisplayName("진행 중인 타석이 있는 상태에서 새로운 타석을 진행할 수 없습니다.")
  void t4() {
  }

  @DisplayName("타석이 종료되면 초기화하여 새로 진행할 수 있습니다.")
  void t5() {
  }

  private Random createRandom(int returnValue) {
    return new Random() {
      @Override
      public int nextInt() {
        return returnValue;
      }
    };
  }
}
