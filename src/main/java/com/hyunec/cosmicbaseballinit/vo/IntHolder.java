package com.hyunec.cosmicbaseballinit.vo;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class IntHolder {
    private Integer value;

    public IntHolder(int inputInt){
        this.value = inputInt;
    }

    public Integer add(Integer number){
        this.value += number;
        return value;
    }

    public int intValue(){
        return this.value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
