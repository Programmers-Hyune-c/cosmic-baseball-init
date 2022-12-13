package com.hyunec.cosmicbaseballinit.service;

import com.hyunec.cosmicbaseballinit.vo.GameResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class Lv1GameService {

    public String gameStart(){
        // 점수 초기화
        Integer myTeamScore = 0;
        Integer otherTeamScore = 0;

        // 게임 진행
        
        
        // 게임 승패 반환
        return this.gameFinished(myTeamScore, otherTeamScore).getResultName();
    }

//    private OneHitterResult oneHitterGame(){
//
//
//    }

    public GameResult gameFinished(Integer myTeamScore, Integer otherTeamScore){
        if(myTeamScore > otherTeamScore){
            return GameResult.WIN;
        }
        if(myTeamScore < otherTeamScore){
            return GameResult.LOSE;
        }
        return GameResult.DRAW;
    }
}
