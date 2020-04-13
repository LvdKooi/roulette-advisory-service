package nl.kooi.app.domain.advises;


import lombok.Getter;
import nl.kooi.app.domain.game.RouletteOneToOne;
import nl.kooi.app.domain.outcome.Outcome;

import java.math.BigDecimal;

/**
 * @author Laurens van der Kooi
 */

@Getter
public class OddEvenAdvice extends RouletteOneToOne {
    private boolean[] hitArray = {true, true};
    public BigDecimal odd;
    public BigDecimal even;

    public OddEvenAdvice(String chipValue, int delay) {
        super(chipValue, delay);

    }

    @Override
    public void setHits(Outcome outcome) {
        hitArray[0] = outcome.getOdd();
        hitArray[1] = outcome.getEven();
        this.setAdvice(hitArray);
    }

    @Override
    protected void setAdvice(boolean[] hitArray) {
        super.setAdvice(hitArray);
        odd = getChipValue().multiply(new BigDecimal(bettingSystem.getAdviceArray()[0]));
        even = getChipValue().multiply(new BigDecimal(bettingSystem.getAdviceArray()[1]));
    }

}
