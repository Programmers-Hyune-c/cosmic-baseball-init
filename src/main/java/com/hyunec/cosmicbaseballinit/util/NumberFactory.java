package com.hyunec.cosmicbaseballinit.util;

import java.util.Random;

public class NumberFactory {
	private static final Random instance = new Random();

	private NumberFactory(){}

	public static Random getInstance() {
		return instance;
	}
}
