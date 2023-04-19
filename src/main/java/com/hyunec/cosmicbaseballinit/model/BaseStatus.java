package com.hyunec.cosmicbaseballinit.model;

import lombok.Getter;

@Getter
public class BaseStatus {
    private boolean firstBase;
    private boolean secondBase;
    private boolean thirdBase;

    public BaseStatus() {
        this.firstBase = false;
        this.secondBase = false;
        this.thirdBase = false;
    }
}
