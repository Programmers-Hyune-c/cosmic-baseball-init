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
    private final Map<String, String> players = new HashMap<>();
    Integer totalOut = 0;
    boolean isPlaying = false;

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

    public boolean fourBallCheck() {
        boolean isFourBallorOut = false;
        if(strikeCount() == 0 && ballCount() == 0) {
            return false;
        }
        if (result().equals(BattingResult.FOUR_BALL) || result().equals(BattingResult.OUT)) {
            return true;
        }
        return isFourBallorOut;

    }

    public void batting() {
        this.isPlaying = true;
        if(!fourBallCheck()){
            batting(Batting.generate());    //4볼이나 OUT이 아니면 리스트에 계속 담는다 > 플레이어 끝
            batting();
            return;
        }
            totalOut++;
            battings.clear();
            this.isPlaying = false;
            if(getTotalOut()<3) {
                return;
            }
            //3OUT - NEW GAME
            if (getTotalOut() == 3) {
                this.totalOut = 0;
            }
    }


    public boolean newGame() throws Exception {
       try{
           if (fourBallCheck()) {
               totalOut = 0;
               battings.clear();
                return true;
           }else{
               Exception e = new Exception("고의로 발생시킴");
               throw e;
           }
       }catch(Exception e) {
           throw new Exception("현재 타석이 진행중이라, 새로운 게임이 불가능 합니다.");
       }
    }


}
