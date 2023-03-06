package com.hyunec.cosmicbaseballinit.acceptancetest;

import static org.assertj.core.api.Assertions.assertThat;

import com.hyunec.cosmicbaseballinit.batting.Batting;
import com.hyunec.cosmicbaseballinit.batting.BattingResults;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


class NormalBaseballLv1Test {

    static List<BattingResults> battingList = new ArrayList<>();

    @BeforeAll()
    static void setup() {
        IntStream.range(0, 100).forEach(i -> battingList.add(Batting.of().getResult()));
    }

    @DisplayName("strike, ball, hit 는 같은 확률 입니다.")
    @Test
    void t1() {
        assertThat(battingList).hasSize(100);
    }

    @DisplayName("타격 결과는 strike, ball, hit 입니다.")
    @Test
    void t2() {
        assertThat(battingList).containsOnly(BattingResults.STRIKE, BattingResults.BALL, BattingResults.HIT);
    }
}
