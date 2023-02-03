package com.hyunec.cosmicbaseballinit.domain.baseball.model.utils.radom;

import com.hyunec.cosmicbaseballinit.domain.baseball.model.Batting;
import java.util.Random;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("equalRandom")
public class EqualRandom implements RandomStrategy{

  private final Random random = new Random();

  @Override
  public int getNumber() {
    return random.nextInt(Batting.getBattingSize());
  }
}
