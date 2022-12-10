package com.hyunec.cosmicbaseballinit.hitter.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.hyunec.cosmicbaseballinit.hitter.domain.Batting.BALL;
import static com.hyunec.cosmicbaseballinit.hitter.domain.Batting.STRIKE;
import static org.assertj.core.api.Assertions.assertThat;

class BattingsTest {

    Battings battings;

    @BeforeEach
    void setUp() {
        battings = new Battings(List.of(
                STRIKE,
                BALL,
                STRIKE
        ));
    }

    @DisplayName("마지막 배팅 기록을 반환한다.")
    @Test
    void lastBatting() {
        Batting actual = battings.lastBatting();

        assertThat(actual).isEqualTo(STRIKE);
    }

    @DisplayName("스트라이크가 몇개 인지 반환한다.")
    @Test
    void battingTypeCount() {
        long strikeCount = battings.battingTypeCount(STRIKE);

        assertThat(strikeCount).isEqualTo(2);
    }

    @DisplayName("배팅 리스트에 추가된다.")
    @Test
    void addBatting() {
        battings.addBatting(STRIKE);

        List<Batting> expected = List.of(STRIKE, BALL, STRIKE, STRIKE);

        assertThat(battings.getBattings()).isEqualTo(expected);
    }

}
