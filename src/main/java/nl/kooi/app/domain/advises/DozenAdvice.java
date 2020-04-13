package nl.kooi.app.domain.advises;


import lombok.Getter;
import nl.kooi.app.domain.game.RouletteTwoToOne;
import nl.kooi.app.domain.outcome.Outcome;

import java.math.BigDecimal;

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
    public void setHits(Outcome roulette) {
        hitArray[0] = roulette.getFirstDozen();
        hitArray[1] = roulette.getSecondDozen();
        hitArray[2] = roulette.getThirdDozen();
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
