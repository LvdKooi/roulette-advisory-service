package nl.kooi.app.domain.game;


import lombok.Getter;
import nl.kooi.app.domain.bettingsystem.BettingAdvice;
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

    public OddEvenGame(String chipValue) {
        super(chipValue);
    }

    @Override
    public void setHits(Outcome outcome) {
        hitArray[0] = outcome.getOdd();
        hitArray[1] = outcome.getEven();
    }

    @Override
    public Map<RouletteOutcome, BigDecimal> getAdvice(boolean[] hitArray) {
        BettingAdvice.twoToOneBettingSystem.compoundDefferedMartingGale(hitArray);
        return BettingAdvice.getOneToOneAdviceMap(ODD, EVEN, getChipValue());
    }

}
