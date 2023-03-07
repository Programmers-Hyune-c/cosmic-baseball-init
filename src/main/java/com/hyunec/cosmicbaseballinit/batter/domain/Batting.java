package com.hyunec.cosmicbaseballinit.batter.domain;

import com.hyunec.cosmicbaseballinit.batter.service.BattingFactory;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class Batting {

    private final BattingFactory factory;

    public static Batting of(BattingFactory factory) {
        return new Batting(factory);
    }
}
