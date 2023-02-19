package com.hyunec.cosmicbaseballinit.acceptancetest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import com.hyunec.cosmicbaseballinit.domain.baseball.model.Batting;
import com.hyunec.cosmicbaseballinit.domain.baseball.model.BattingResult;
import com.hyunec.cosmicbaseballinit.domain.baseball.model.PlateAppearances;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.hyunec.cosmicbaseballinit.domain.baseball.exception.ExceptionMessage;

import java.util.Random;


class NormalBaseballLv1Test {


    @DisplayName("strike, ball, hit 는 같은 확률 입니다.")
    @Test
    void t1() {

        int nDefalutNo = 100;
        Random random = new Random();
        int n1 = 0, n2 = 0, n3 = 0;
        for (int i = 0; i < nDefalutNo; i++) {
            Batting nResult = Batting.generate();
            if (nResult == Batting.BALL) {
                n1++;
            }

            if (nResult == Batting.STRIKE) {
                n2++;
            }

            if (nResult == Batting.HIT) {
                n3++;
            }
        }

        System.out.println(
                "[ BALL ]" + Math.floor((double) n1 / nDefalutNo * nDefalutNo)
                        + " -- "
                        + "[ STRIKE ]" + Math.floor((double) n2 / nDefalutNo * nDefalutNo)
                        + " -- "
                        + "[ HIT ]" + Math.floor((double) n3 / nDefalutNo * nDefalutNo)
        );

        boolean result = compareThreeValues((int) n1, (int) n2, (int) n3);
        assertEquals(result, true);
    }

    public static boolean compareThreeValues(int n1, int n2, int n3) {
        boolean answer = false;
        boolean equation1 = 0 <= Math.abs(n2 - n1) / 100 && Math.abs(n2 - n1) / 100 < 5;
        boolean equation2 = 0 <= Math.abs(n3 - n2) / 100 && Math.abs(n3 - n2) / 100 < 5;
        if (equation1 && equation2) {
            answer = true;
        }
        return answer;

    }

    @DisplayName("3B 타석에서 타격 결과가 ball 이면 타석 결과는 four_ball 됩니다.")
    @Test
    void t2() {

        String battingType = "BALL";

        //given : 3B 타석
        PlateAppearances pa = new PlateAppearances();
        pa.batting(Batting.valueOf(battingType));
        pa.batting(Batting.valueOf(battingType));
        pa.batting(Batting.valueOf(battingType));

        // when: 결과가 ball 이면
        pa.batting(Batting.valueOf(battingType));

        // then : 4ball 이 됨.
        assertThat(pa.result()).isEqualTo(BattingResult.FOUR_BALL);

    }

    @DisplayName("2S 타석에서 타격 결과가 strike 이면 타석 결과는 out 됩니다.")
    @Test
    void t3() {

        String battingType = "STRIKE";
        //given : 2S 타석
        PlateAppearances pa = new PlateAppearances();
        pa.batting(Batting.valueOf(battingType));
        pa.batting(Batting.valueOf(battingType));

        //when : 결과가 strike 이면
        pa.batting(Batting.valueOf(battingType));

        //then : 타석결과는 out 인지 검증
        assertThat(pa.result()).isEqualTo(BattingResult.OUT);

    }

    @DisplayName("진행 중인 타석이 있는 상태에서 새로운 타석을 진행할 수 없습니다.")
    @Test
    void t4() {

        //given
        PlateAppearances pa = new PlateAppearances();
        pa.batting(Batting.valueOf("STRIKE"));
        pa.batting(Batting.valueOf("BALL"));
        pa.batting(Batting.valueOf("BALL"));
        pa.batting(Batting.valueOf("STRIKE"));
        pa.batting(Batting.valueOf("BALL"));
        //when, then
        assertThat(pa.newGame("")).isEqualTo(ExceptionMessage.CANNOT_PROCEED_NEWGAME);
        pa.newGame("force");
    }


    @DisplayName("타석이 종료되면 초기화하여 새로 진행할 수 있습니다.")
    @Test
    void t5() {

        //given
        PlateAppearances pa = new PlateAppearances();
        pa.batting(Batting.valueOf("BALL"));
        pa.batting(Batting.valueOf("BALL"));
        pa.batting(Batting.valueOf("BALL"));
        pa.batting(Batting.valueOf("BALL"));

        //when, then
        assertThat(pa.newGame("")).isEqualTo(ExceptionMessage.NEW_GAME_START);

    }
}
