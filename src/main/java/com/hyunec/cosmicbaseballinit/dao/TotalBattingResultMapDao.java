package com.hyunec.cosmicbaseballinit.dao;

import com.hyunec.cosmicbaseballinit.domain.TotalBattingResult;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.stereotype.Repository;

@Repository
public class TotalBattingResultMapDao implements TotalBattingResultDao {

    private final Map<Long, TotalBattingResult> store = new ConcurrentHashMap<>();
    private final AtomicLong sequence = new AtomicLong(0);

    @Override
    public TotalBattingResult save(TotalBattingResult totalBattingResult) {
        long id = sequence.incrementAndGet();
        totalBattingResult.setId(id);
        store.put(id, totalBattingResult);
        return store.get(id);
    }

    @Override
    public TotalBattingResult update(TotalBattingResult totalBattingResult) {
        store.put(totalBattingResult.getId(), totalBattingResult);
        return store.get(totalBattingResult.getId());
    }

    @Override
    public TotalBattingResult findById(Long id) {
        return store.get(id);
    }

    @Override
    public void delete(Long id) {
        store.remove(id);
    }
}

