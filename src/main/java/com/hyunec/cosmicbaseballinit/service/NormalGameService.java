package com.hyunec.cosmicbaseballinit.service;

import com.hyunec.cosmicbaseballinit.dao.ScoreBoardDao;
import com.hyunec.cosmicbaseballinit.domain.BaseList;
import com.hyunec.cosmicbaseballinit.domain.BattingResult;
import com.hyunec.cosmicbaseballinit.domain.NormalScoreBoard;
import com.hyunec.cosmicbaseballinit.domain.ScoreBoard;
import com.hyunec.cosmicbaseballinit.util.RandomBattingResultGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class NormalGameService implements GameService {

    private final ScoreBoardDao normalScoreBoardMapDaoImpl;

    @Override
    public ScoreBoard newGame() {
        return normalScoreBoardMapDaoImpl.save(new NormalScoreBoard(new BaseList()));
    }

    @Override
    public ScoreBoard batting(Long id, int percentage, BattingResult targetResult) {
        BattingResult battingResult =
            RandomBattingResultGenerator.get(percentage, targetResult);
        return updateAtBatResult(id, battingResult);
    }

    private ScoreBoard updateAtBatResult(Long id, BattingResult result) {
        ScoreBoard normalScoreBoardEntity = normalScoreBoardMapDaoImpl.findById(id);
        normalScoreBoardEntity.adjust(result);
        return normalScoreBoardEntity;
    }

}
