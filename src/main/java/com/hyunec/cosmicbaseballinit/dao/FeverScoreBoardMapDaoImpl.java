package com.hyunec.cosmicbaseballinit.dao;

import com.hyunec.cosmicbaseballinit.domain.ScoreBoard;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.stereotype.Repository;

@Repository
public class FeverScoreBoardMapDaoImpl implements ScoreBoardDao {

    private final Map<Long, ScoreBoard> store = new ConcurrentHashMap<>();
    private final AtomicLong sequence = new AtomicLong(0);

    @Override
    public ScoreBoard save(ScoreBoard feverScoreBoard) {
        long id = sequence.incrementAndGet();
        feverScoreBoard.setId(id);
        store.put(id, feverScoreBoard);
        return store.get(id);
    }

    @Override
    public ScoreBoard findById(Long id) {
        return store.get(id);
    }

}
