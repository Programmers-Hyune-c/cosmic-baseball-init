package com.hyunec.cosmicbaseballinit.service.lv1;

import com.hyunec.cosmicbaseballinit.vo.GameResult;
import com.hyunec.cosmicbaseballinit.vo.ProbabilityType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class Lv1GameService {

    public void gameStart() throws Exception {
        // 점수 초기화
        Integer myTeamScore = 0;
        Integer otherTeamScore = 0;
        Integer round = 1;

        // 게임 진행
        Lv1HitterGame lv1HitterGame = new Lv1HitterGame(ProbabilityType.SAME);
        for (int i = 0; i < round; i ++) {
            log.info("-------round {}--------", round);
            log.info("-------My team -----------");
            lv1HitterGame.oneHitterGameStart();
            log.info("-------Other team--------");
            lv1HitterGame.oneHitterGameStart();
        }
        GameResult gameResult = returnGameResult(myTeamScore, otherTeamScore);
        log.info("--------gameResult : {}-------", gameResult);
    }

    public GameResult returnGameResult(Integer myTeamScore, Integer otherTeamScore) {
        if(myTeamScore > otherTeamScore) {
            return GameResult.WIN;
        }
        if(myTeamScore < otherTeamScore) {
            return GameResult.LOSE;
        }
        return GameResult.DRAW;
    }
}
