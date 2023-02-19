package com.hyunec.cosmicbaseballinit.domain.baseball.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.hyunec.cosmicbaseballinit.domain.baseball.model.Batting.BALL;
import static com.hyunec.cosmicbaseballinit.domain.baseball.model.Batting.STRIKE;

import com.hyunec.cosmicbaseballinit.domain.baseball.exception.ExceptionMessage;

@Getter
@RequiredArgsConstructor
@Component
public class PlateAppearances {

    private final List<Batting> battings = new ArrayList<>();
    private final Map<String, String> players = new HashMap<String, String>();

//    private static PlateAppearances pa;

    Integer totalOut = 0;
    boolean isPlaying = false;


//    public static synchronized PlateAppearances getInstance() {
//        if(pa == null) {
//            pa = new PlateAppearances();
//        }
//        return pa;
//    }

//    public void setPlaying(boolean is) {
//        this.isPlaying = is;
//    }
//
//    public void setTotalOut() {
//        this.totalOut++;
//    }

    public void batting(final Batting batting) {
        this.isPlaying = true;
        battings.add(batting);
    }

    public Integer strikeCount() {
        return Math.toIntExact(battings.stream().filter(STRIKE::equals).count());
    }

    public Integer ballCount() {
        return Math.toIntExact(battings.stream().filter(BALL::equals).count());
    }

    public BattingResult result() {
        if (strikeCount().equals(BattingResult.OUT.getValue())) {
            this.isPlaying = false;
            return BattingResult.OUT;
        }

        if (ballCount().equals(BattingResult.FOUR_BALL.getValue())) {
            this.isPlaying = false;
            return BattingResult.FOUR_BALL;
        }

        return BattingResult.of(battings.get(battings.size() - 1));
    }

    public void clear() {
        battings.clear();
    }


    public boolean fourBallCheck() {
        boolean isFourBallorOut = false;
        if (!(strikeCount() == 0 && ballCount() == 0)) {
            if (result().equals(BattingResult.FOUR_BALL) || result().equals(BattingResult.OUT)) {
                isFourBallorOut = true;
            }
        }
        return isFourBallorOut;
    }

    public void batting() {
        this.isPlaying = true;

        if (fourBallCheck()) {
            totalOut++;
            battings.clear();
            this.isPlaying = false;

            //3OUT - NEW CHANGE GAME
            if (getTotalOut() == 3) {
                this.totalOut = 0;
            }
            return;
        }
        batting(Batting.generate());    //4볼이나 OUT이 아니면 리스트에 계속 담는다 > 플레이어 끝
        batting();
    }


    public String newGame(String str) {
        if ("force".equals(str)) {
            totalOut = 0;
            battings.clear();
            return null;
        }
        if (fourBallCheck()) {
            totalOut = 0;
            battings.clear();
            return ExceptionMessage.NEW_GAME_START;
        }
        return ExceptionMessage.CANNOT_PROCEED_NEWGAME;
    }

}
