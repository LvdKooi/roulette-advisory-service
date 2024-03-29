package nl.kooi.app.domain.game;


import lombok.Getter;
import nl.kooi.app.domain.advice.Advice;
import nl.kooi.app.domain.outcome.Outcome;
import nl.kooi.app.domain.rouletteoutcome.RouletteOutcome;

import java.math.BigDecimal;
import java.util.Map;

import static nl.kooi.app.domain.rouletteoutcome.RouletteOutcome.*;

/**
 * @author Laurens van der Kooi
 */
@Getter
public class DozenGame extends RouletteTwoToOne {

    private boolean[] hitArray = {true, true, true};

    public DozenGame(BigDecimal chipValue) {
        super(chipValue);
    }

    @Override
    public void setHits(Outcome roulette) {
        hitArray[0] = roulette.getFirstDozen();
        hitArray[1] = roulette.getSecondDozen();
        hitArray[2] = roulette.getThirdDozen();
        getTwoToOneBettingSystem().setHits(hitArray);
    }

    @Override
    public Advice getAdvice() {
        return getTwoToOneBettingSystem().getTwoToOneAdvice(FIRST_DOZEN, SECOND_DOZEN, THIRD_DOZEN, getChipValue());
    }
}
