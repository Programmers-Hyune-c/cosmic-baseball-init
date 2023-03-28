package com.hyunec.cosmicbaseballinit.service;

import com.hyunec.cosmicbaseballinit.dao.ScoreBoardDao;
import com.hyunec.cosmicbaseballinit.domain.BattingResult;
import com.hyunec.cosmicbaseballinit.domain.ScoreBoard;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class GameService {

    private final ScoreBoardDao scoreBoardDao;

    public ScoreBoard newGame() {
        return scoreBoardDao.save(new ScoreBoard());
    }

    public ScoreBoard batting(Long id, int percentage, BattingResult targetResult) {
        BattingResult battingResult =
            RandomBattingResultGenerator.get(percentage, targetResult);
        return updateTotalBattingResult(id, battingResult);
    }

    private ScoreBoard updateTotalBattingResult(
        Long id,
        BattingResult result
    ) {
        ScoreBoard scoreBoardEntity = scoreBoardDao.findById(id);
        scoreBoardEntity.adjust(result);
        return scoreBoardEntity;
    }
}
