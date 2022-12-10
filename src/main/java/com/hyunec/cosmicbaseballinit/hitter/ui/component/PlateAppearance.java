package com.hyunec.cosmicbaseballinit.hitter.ui.component;

import com.hyunec.cosmicbaseballinit.hitter.domain.Batting;
import com.hyunec.cosmicbaseballinit.hitter.domain.Battings;
import com.hyunec.cosmicbaseballinit.hitter.domain.BattingsResult;
import org.springframework.stereotype.Component;

@Component
public class PlateAppearance {

    public String result(final Battings battings) {

        if ( battings.battingTypeCount(Batting.STRIKE) >= BattingsResult.OUT.getValue()) {
            return BattingsResult.OUT.name();
        }

        if( battings.battingTypeCount(Batting.BALL) >= BattingsResult.FOUR_BALL.getValue() ) {
            return BattingsResult.FOUR_BALL.name();
        }

        return battings.lastBatting().name();
    }


}
