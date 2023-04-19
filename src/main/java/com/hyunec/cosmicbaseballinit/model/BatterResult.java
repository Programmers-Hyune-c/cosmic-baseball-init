package com.hyunec.cosmicbaseballinit.model;

import lombok.Getter;

@Getter
public class BatterResult {
    private int strikeCount;
    private int ballCount;
    private int hitCount;

    public BatterResult() {
        this.strikeCount = 0;
        this.ballCount = 0;
        this.hitCount = 0;
    }
}
