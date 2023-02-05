package com.hyunec.cosmicbaseballinit.acceptancetest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

import com.hyunec.cosmicbaseballinit.domain.baseball.model.Batting;
import com.hyunec.cosmicbaseballinit.domain.baseball.model.utils.generator.BattingGenerator;
import com.hyunec.cosmicbaseballinit.domain.baseball.model.utils.generator.RandomBattingGenerator;
import com.hyunec.cosmicbaseballinit.domain.baseball.model.utils.generator.radom.RandomStrategy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class NormalBaseballLv1Test {


  @Mock
  private RandomStrategy randomStrategy;

  @DisplayName("strike, ball, hit 는 같은 확률 입니다.")
  @ParameterizedTest
  @ValueSource(ints = {0, 1, 2, 3, 4, 5, Integer.MAX_VALUE})
  void t1(int number) {
    //given
    int MAX_NUMBER = 3;
    BattingGenerator battingGenerator = new RandomBattingGenerator(randomStrategy);
    given(randomStrategy.getNumber()).willReturn(number);

    //when
    Batting generateValue = battingGenerator.generator();

    //then
    assertThat(generateValue).isEqualTo(Batting.of(number % MAX_NUMBER));
  }

  @DisplayName("3B 타석에서 타격 결과가 ball 이면 타석 결과는 four_ball 됩니다.")
  @Test
  void t2() {
    throw new RuntimeException("Not yet implemented");
  }

  @DisplayName("2S 타석에서 타격 결과가 strike 이면 타석 결과는 out 됩니다.")
  @Test
  void t3() {
    throw new RuntimeException("Not yet implemented");
  }

  @DisplayName("진행 중인 타석이 있는 상태에서 새로운 타석을 진행할 수 없습니다.")
  @Test
  void t4() {
    throw new RuntimeException("Not yet implemented");
  }

  @DisplayName("타석이 종료되면 초기화하여 새로 진행할 수 있습니다.")
  @Test
  void t5() {
    throw new RuntimeException("Not yet implemented");
  }
}
