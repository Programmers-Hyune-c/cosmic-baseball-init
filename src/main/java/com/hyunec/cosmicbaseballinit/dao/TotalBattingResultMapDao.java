package com.hyunec.cosmicbaseballinit.dao;

import com.hyunec.cosmicbaseballinit.domain.TotalBattingResult;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Repository;

@Repository
public class TotalBattingResultMapDao implements TotalBattingResultDao {

    private final Map<Long, TotalBattingResult> store = new ConcurrentHashMap<>();

    @Override
    public TotalBattingResult save(TotalBattingResult totalBattingResult) {
        store.put(totalBattingResult.getId(), totalBattingResult);
        return totalBattingResult;
    }

    @Override
    public TotalBattingResult update(Long id, TotalBattingResult totalBattingResult) {
        store.put(id, totalBattingResult);
        return store.get(id);
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

