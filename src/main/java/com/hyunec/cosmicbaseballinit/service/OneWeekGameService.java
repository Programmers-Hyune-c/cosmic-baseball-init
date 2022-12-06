package com.hyunec.cosmicbaseballinit.service;

import com.hyunec.cosmicbaseballinit.vo.GameResult;
import com.hyunec.cosmicbaseballinit.vo.OneHitterResult;
import org.springframework.stereotype.Service;

@Service
public class OneWeekGameService implements GameService {

    private Integer rounds;
    private Integer myTeamScore;
    private Integer otherTeamScore;

    public OneWeekGameService() {
        this.myTeamScore = 0;
        this.otherTeamScore = 0;
    }

    public String gameStarted(){

        return this.gameFinished(myTeamScore, otherTeamScore).getResultName();
    }

//    private OneHitterResult oneHitterGame(){
//
//
//    }

    public GameResult gameFinished(Integer myTeamScore, Integer otherTeamScore){
        if(myTeamScore > otherTeamScore){
            return GameResult.WIN;
        }else if(myTeamScore < otherTeamScore){
            return GameResult.LOSE;
        }
        return GameResult.DRAW;
    }
}
