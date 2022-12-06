package com.hyunec.cosmicbaseballinit.hitter.domain;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class BattingReposityImpl implements BattingRepository {

    private static List<Batting> battingList = new ArrayList<>();

    @Override
    public List<Batting> findAll() {
        return List.copyOf(battingList);
    }

    @Override
    public void save(final Batting batting) {
        battingList.add(batting);
    }

    @Override
    public void clear() {
        battingList = new ArrayList<>();
    }
}
