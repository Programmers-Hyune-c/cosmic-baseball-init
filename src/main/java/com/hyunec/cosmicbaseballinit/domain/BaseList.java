package com.hyunec.cosmicbaseballinit.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
public class BaseList {

    private static final int BASES_SIZE = 4;
    private final List<Integer> onBaseList = new ArrayList<>();

    public BaseList(List<Integer> list) {
        onBaseList.addAll(list);
    }

    public void addBase() {
        onBaseList.add(1);
    }

    @JsonIgnore
    public boolean isNormalRun() {
        return BASES_SIZE == onBaseList.size();
    }

    @JsonIgnore
    public boolean isFeverRun() {
        return getBaseSizeRemovedThird() == onBaseList.size();
    }

    private int getBaseSizeRemovedThird() {
        return BASES_SIZE - 1;
    }

    public void modifyBaseList() {
        onBaseList.remove(0);
    }
}
