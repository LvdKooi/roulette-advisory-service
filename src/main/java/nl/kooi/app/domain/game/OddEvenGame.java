package nl.kooi.app.domain.game;


import lombok.Getter;
import nl.kooi.app.domain.outcome.Outcome;
import nl.kooi.app.domain.rouletteoutcome.RouletteOutcome;

import java.math.BigDecimal;
import java.util.Map;

import static nl.kooi.app.domain.rouletteoutcome.RouletteOutcome.EVEN;
import static nl.kooi.app.domain.rouletteoutcome.RouletteOutcome.ODD;

/**
 * @author Laurens van der Kooi
 */

@Getter
public class OddEvenGame extends RouletteOneToOne {
    private boolean[] hitArray = {true, true};

    public OddEvenGame(BigDecimal chipValue) {
        super(chipValue);
    }

    @Override
    public void setHits(Outcome outcome) {
        hitArray[0] = outcome.getOdd();
        hitArray[1] = outcome.getEven();
        getOneToOneBettingSystem().compoundDefferedMartingGale(hitArray);
    }

    @Override
    public Map<RouletteOutcome, BigDecimal> getAdvice() {
        return getOneToOneBettingSystem().getOneToOneAdviceMap(ODD, EVEN, getChipValue());
    }

}
