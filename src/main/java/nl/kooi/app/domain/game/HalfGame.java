package nl.kooi.app.domain.game;

import lombok.Getter;
import nl.kooi.app.domain.outcome.Outcome;
import nl.kooi.app.domain.rouletteoutcome.RouletteOutcome;

import java.math.BigDecimal;
import java.util.Map;

import static nl.kooi.app.domain.rouletteoutcome.RouletteOutcome.FIRST_HALF;
import static nl.kooi.app.domain.rouletteoutcome.RouletteOutcome.SECOND_HALF;

/**
 * @author Laurens van der Kooi
 */

@Getter
public class HalfGame extends RouletteOneToOne {

    private boolean[] hitArray = {true, true};

    public HalfGame(BigDecimal chipValue) {
        super(chipValue);
    }

    @Override
    public void setHits(Outcome roulette) {
        hitArray[0] = roulette.getFirstHalf();
        hitArray[1] = roulette.getSecondHalf();
        getOneToOneBettingSystem().compoundDefferedMartingGale(hitArray);
    }

    @Override
    public Map<RouletteOutcome, BigDecimal> getAdvice() {
        return getOneToOneBettingSystem().getOneToOneAdviceMap(FIRST_HALF, SECOND_HALF, getChipValue());
    }

}
