package com.hyunec.cosmicbaseballinit.dao;

import com.hyunec.cosmicbaseballinit.domain.ScoreBoard;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.stereotype.Repository;

@Repository
public class NormalScoreBoardMapDaoImpl implements ScoreBoardDao {

    private final Map<Long, ScoreBoard> store = new ConcurrentHashMap<>();
    private final AtomicLong sequence = new AtomicLong(0);

    @Override
    public ScoreBoard save(ScoreBoard normalScoreBoard) {
        long id = sequence.incrementAndGet();
        normalScoreBoard.setId(id);
        store.put(id, normalScoreBoard);
        return store.get(id);
    }

    @Override
    public ScoreBoard findById(Long id) {
        return store.get(id);
    }
}

