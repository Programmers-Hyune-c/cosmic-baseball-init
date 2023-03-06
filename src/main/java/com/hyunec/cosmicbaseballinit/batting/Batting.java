package com.hyunec.cosmicbaseballinit.batting;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class Batting {

    private final BattingResults result;

    public static Batting of() {
        BattingStrategy strategy = new RandomBattingStrategy();
        return new Batting(strategy.generateResult());
    }

    @Override
    public String toString() {
        return result.name();
    }
}
