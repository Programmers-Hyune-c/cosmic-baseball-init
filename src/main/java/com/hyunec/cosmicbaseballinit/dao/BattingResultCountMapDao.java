package com.hyunec.cosmicbaseballinit.dao;

import com.hyunec.cosmicbaseballinit.domain.TotalBattingResult;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.stereotype.Repository;

@Repository
public class BattingResultCountMapDao implements BattingResultCountDao {

    private final Map<Long, TotalBattingResult> store = new ConcurrentHashMap<>();
    private final AtomicLong number = new AtomicLong(0);

    @Override
    public void save(TotalBattingResult totalBattingResult) {
        long id = number.incrementAndGet();
        totalBattingResult.setId(id);
        store.put(id, totalBattingResult);
    }

    @Override
    public void update(Long id, TotalBattingResult totalBattingResult) {
        store.put(id, totalBattingResult);
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

