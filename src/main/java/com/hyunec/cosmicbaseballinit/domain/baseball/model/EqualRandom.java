package com.hyunec.cosmicbaseballinit.domain.baseball.model;

import java.util.Random;
import org.springframework.stereotype.Component;

@Component
public class EqualRandom implements RandomStrategy{

  private final Random random = new Random();

  @Override
  public int getNumber() {
    return random.nextInt();
  }
}
