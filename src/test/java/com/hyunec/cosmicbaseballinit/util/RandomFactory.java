package com.hyunec.cosmicbaseballinit.util;

import java.util.Random;

public class RandomFactory {

    private static Random getInstance = new Random();

    private RandomFactory(){};
    public static Random getInstance(){
       return getInstance;
    }
}
