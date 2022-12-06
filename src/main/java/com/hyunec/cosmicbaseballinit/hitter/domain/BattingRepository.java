package com.hyunec.cosmicbaseballinit.hitter.domain;

import java.util.List;

public interface BattingRepository {

    List<Batting> findAll();

    void save(Batting batting);

    void clear();
}
