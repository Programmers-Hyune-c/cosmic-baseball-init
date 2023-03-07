package com.hyunec.cosmicbaseballinit.batter.domain;

import com.hyunec.cosmicbaseballinit.batter.service.BattingStrategy;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class Batting {

    private final BattingResult result;

    public static Batting of(BattingStrategy strategy) {
        return new Batting(strategy.generateBatting());
    }

    @Override
    public String toString() {
        return result.name();
    }
}
