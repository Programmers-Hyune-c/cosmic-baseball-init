package com.hyunec.cosmicbaseballinit.domain.baseball.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PlateAppearancesTest {

    @DisplayName("타석 결과 - 아웃")
    @Test
    void atBatResultOut() {
        // given
        final PlateAppearances plateAppearances = new PlateAppearances();
        plateAppearances.batting(Batting.STRIKE);
        plateAppearances.batting(Batting.STRIKE);

        // when
        assertThat(plateAppearances.result()).isEqualTo(BattingResult.STRIKE);
        plateAppearances.batting(Batting.STRIKE);

        // then
        assertThat(plateAppearances.result()).isEqualTo(BattingResult.OUT);
    }

    
    @DisplayName("타석 결과 - 포볼")
    @Test
    void atBatResultsFourBall() {
        // given
        final PlateAppearances plateAppearances = new PlateAppearances();
        plateAppearances.batting(Batting.BALL);
        plateAppearances.batting(Batting.BALL);
        plateAppearances.batting(Batting.BALL);

        // when
        assertThat(plateAppearances.result()).isEqualTo(BattingResult.BALL);
        plateAppearances.batting(Batting.BALL);

        // then
        assertThat(plateAppearances.result()).isEqualTo(BattingResult.FOUR_BALL);
    }

    @DisplayName("타석 결과 - 안타")
    @Test
    void atBatResultHits() {
        // given
        final PlateAppearances plateAppearances = new PlateAppearances();

        // when
        plateAppearances.batting(Batting.HIT);

        // then
        assertThat(plateAppearances.result()).isEqualTo(BattingResult.HIT);
    }
}
