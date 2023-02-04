package com.hyunec.cosmicbaseballinit.domain.baseball.model.base;

import static org.assertj.core.api.Assertions.assertThat;

import com.hyunec.cosmicbaseballinit.domain.baseball.model.hit.HitType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class BaseTest {

  @Nested
  @DisplayName("1루타를 쳤을 때, 동작 검증")
  class SingleHit {
    @DisplayName("1루타를 쳤을때, 제대로 동작하는지 확인")
    @Test
    void atSingleHit() {
      //given
      Base base = new Base();

      //when, then
      assertThat(base.hit(HitType.SINGLE_HIT)).isEqualTo(0);
      assertThat(base.checkPlateExist(BaseType.FIRST_BASE)).isTrue();
      assertThat(base.checkPlateExist(BaseType.SECOND_BASE)).isFalse();
      assertThat(base.checkPlateExist(BaseType.THIRD_BASE)).isFalse();
    }

    @DisplayName("1루에 사람이 있을경우, 1루타를 쳤을때 제대로 동작하는지 확인")
    @Test
    void atFirstBaseExistsSingleHit() {
      //given
      Base base = new Base();

      //when
      base.hit(HitType.SINGLE_HIT);

      //then
      assertThat(base.hit(HitType.SINGLE_HIT)).isEqualTo(0);
      assertThat(base.checkPlateExist(BaseType.FIRST_BASE)).isTrue();
      assertThat(base.checkPlateExist(BaseType.SECOND_BASE)).isTrue();
      assertThat(base.checkPlateExist(BaseType.THIRD_BASE)).isFalse();
    }

    @DisplayName("1루, 2루에 사람이 있을경우, 1루타를 쳤을때 제대로 동작하는지 확인")
    @Test
    void atFirstAndSecondBaseExistsSingleHit() {
      //given
      Base base = new Base();

      //when
      base.hit(HitType.SINGLE_HIT);
      base.hit(HitType.SINGLE_HIT);

      //then
      assertThat(base.hit(HitType.SINGLE_HIT)).isEqualTo(0);
      assertThat(base.checkPlateExist(BaseType.FIRST_BASE)).isTrue();
      assertThat(base.checkPlateExist(BaseType.SECOND_BASE)).isTrue();
      assertThat(base.checkPlateExist(BaseType.THIRD_BASE)).isTrue();
    }

    @DisplayName("만루일 경우, 1루타를 쳤을때, 득점을 반환하는지 확인")
    @Test
    void atAllPlateExistsSingleHitGetScore() {
      //given
      Base base = new Base();

      //when
      base.hit(HitType.SINGLE_HIT);
      base.hit(HitType.SINGLE_HIT);
      base.hit(HitType.SINGLE_HIT);

      //then
      assertThat(base.hit(HitType.SINGLE_HIT)).isEqualTo(1);
    }
  }
}