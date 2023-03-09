package com.hyunec.cosmicbaseballinit.dao;

import com.hyunec.cosmicbaseballinit.domain.BattingResultCount;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.stereotype.Repository;

@Repository
public class BattingResultCountMapDao implements BattingResultCountDao {

    private final Map<Long, BattingResultCount> store = new ConcurrentHashMap<>();
    private final AtomicLong number = new AtomicLong(0);

    @Override
    public void save(BattingResultCount battingResultCount) {
        long id = number.incrementAndGet();
        battingResultCount.setId(id);
        store.put(id, battingResultCount);
    }

    @Override
    public void update(Long id, BattingResultCount battingResultCount) {
        store.put(id, battingResultCount);
    }

    @Override
    public BattingResultCount findById(Long id) {
        return store.get(id);
    }
}

