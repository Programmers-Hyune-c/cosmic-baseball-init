package com.hyunec.cosmicbaseballinit.common;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Random;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RandomGenerator {
    public static final Random RANDOM = new Random();
}
