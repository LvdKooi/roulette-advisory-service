package nl.kooi.app.domain.advises;


import lombok.Getter;
import nl.kooi.app.domain.game.RouletteTwoToOne;
import nl.kooi.app.domain.rouletteoutcome.CompoundRouletteOutcome;

import java.math.BigDecimal;

import static nl.kooi.app.domain.rouletteoutcome.RouletteOutcome.*;

/**
 * @author Laurens van der Kooi
 */
@Getter
public class DozenAdvice extends RouletteTwoToOne {

    private boolean[] hitArray = {true, true, true};
    public BigDecimal firstDozen;
    public BigDecimal secondDozen;
    public BigDecimal thirdDozen;

    public DozenAdvice(String chipValue) {
        super(chipValue);
    }

    @Override
    public void setHits(CompoundRouletteOutcome roulette) {
        hitArray[0] = roulette.getOutcomeBooleanMap().get(FIRST_DOZEN);
        hitArray[1] = roulette.getOutcomeBooleanMap().get(SECOND_DOZEN);
        hitArray[2] = roulette.getOutcomeBooleanMap().get(THIRD_DOZEN);
        this.setAdvice(hitArray);
    }

    @Override
    protected void setAdvice(boolean[] hitArray) {
        super.setAdvice(hitArray);
        firstDozen = getChipValue().multiply(new BigDecimal(bettingSystem.getAdviceArray()[0]));
        secondDozen = getChipValue().multiply(new BigDecimal(bettingSystem.getAdviceArray()[1]));
        thirdDozen = getChipValue().multiply(new BigDecimal(bettingSystem.getAdviceArray()[2]));
    }

}
