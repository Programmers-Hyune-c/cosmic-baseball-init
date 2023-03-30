package com.hyunec.cosmicbaseballinit.service;

import com.hyunec.cosmicbaseballinit.dao.ScoreBoardDao;
import com.hyunec.cosmicbaseballinit.domain.BaseList;
import com.hyunec.cosmicbaseballinit.domain.BattingResult;
import com.hyunec.cosmicbaseballinit.domain.FeverScoreBoard;
import com.hyunec.cosmicbaseballinit.domain.ScoreBoard;
import com.hyunec.cosmicbaseballinit.util.RandomBattingResultGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class FeverGameService implements GameService {

    private final ScoreBoardDao feverScoreBoardMapDaoImpl;

    @Override
    public ScoreBoard newGame() {
        return feverScoreBoardMapDaoImpl.save(new FeverScoreBoard(new BaseList()));
    }

    @Override
    public ScoreBoard batting(Long id, int percentage, BattingResult targetResult) {
        BattingResult battingResult =
            RandomBattingResultGenerator.getOnFever(percentage, targetResult);
        return updateAtBatResult(id, battingResult);
    }

    private ScoreBoard updateAtBatResult(Long id, BattingResult battingResult) {
        ScoreBoard feverScoreBoardEntity = feverScoreBoardMapDaoImpl.findById(id);
        feverScoreBoardEntity.adjust(battingResult);
        return feverScoreBoardEntity;
    }

}
