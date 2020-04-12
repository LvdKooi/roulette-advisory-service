package nl.kooi.app.domain.advises;

import lombok.Getter;
import nl.kooi.app.domain.game.RouletteOneToOne;
import nl.kooi.app.domain.rouletteoutcome.CompoundRouletteOutcome;

import java.math.BigDecimal;

import static nl.kooi.app.domain.rouletteoutcome.RouletteOutcome.FIRST_HALF;
import static nl.kooi.app.domain.rouletteoutcome.RouletteOutcome.SECOND_HALF;

/**
 * @author Laurens van der Kooi
 */

@Getter
public class HalfAdvice extends RouletteOneToOne {

    private boolean[] hitArray = {true, true};
    public BigDecimal firstHalf;
    public BigDecimal secondHalf;

    public HalfAdvice(String chipValue, int delay) {
        super(chipValue, delay);
    }

    @Override
    public void setHits(CompoundRouletteOutcome roulette) {
        hitArray[0] = roulette.getOutcomeBooleanMap().get(FIRST_HALF);
        hitArray[1] = roulette.getOutcomeBooleanMap().get(SECOND_HALF);
        this.setAdvice(hitArray);

    }

    @Override
    protected void setAdvice(boolean[] hitArray) {
        super.setAdvice(hitArray);
        firstHalf = getChipValue().multiply(new BigDecimal(bettingSystem.getAdviceArray()[0]));
        secondHalf = getChipValue().multiply(new BigDecimal(bettingSystem.getAdviceArray()[1]));
    }

}
