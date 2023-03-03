package com.hyunec.cosmicbaseballinit.acceptancetest;

import java.util.Random;

public class ResultCount {
    public int getBattingResultCount(int resultNumber, int count, Random random, int enumCount) {
        for (int i = 0; i < 100_000; i++) {
            int randomNumber = random.nextInt(enumCount);
            count = addCount(resultNumber, count, randomNumber);
        }
        return count;
    }

    private int addCount(final int resultNumber, int count, final int randomNumber) {
        if (resultNumber == randomNumber) {
            count++;
        }
        return count;
    }

}
