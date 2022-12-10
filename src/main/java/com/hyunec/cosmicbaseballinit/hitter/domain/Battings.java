package com.hyunec.cosmicbaseballinit.hitter.domain;

import lombok.Getter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Getter
public class Battings {

    private List<Batting> battings;

    public Battings(List<Batting> battings) {
        this.battings = Collections.unmodifiableList(battings);
    }

    public Batting lastBatting() {
        return battings.get(battings.size() - 1);
    }

    public long battingTypeCount(Batting battingType) {
        return battings.stream()
                .filter(batting -> batting.equals(battingType))
                .count();
    }

    public void addBatting(Batting batting) {
        List<Batting> updateBattings = new ArrayList<>(battings);
        updateBattings.add(batting);

        this.battings = Collections.unmodifiableList(updateBattings);
    }
}
