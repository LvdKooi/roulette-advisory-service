package nl.kooi.app.domain.advises;

import lombok.Getter;
import nl.kooi.app.domain.game.RouletteOneToOne;
import nl.kooi.app.domain.outcome.Outcome;

import java.math.BigDecimal;

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
    public void setHits(Outcome roulette) {
        hitArray[0] = roulette.getFirstHalf();
        hitArray[1] = roulette.getSecondHalf();
        this.setAdvice(hitArray);

    }

    @Override
    protected void setAdvice(boolean[] hitArray) {
        super.setAdvice(hitArray);
        firstHalf = getChipValue().multiply(new BigDecimal(bettingSystem.getAdviceArray()[0]));
        secondHalf = getChipValue().multiply(new BigDecimal(bettingSystem.getAdviceArray()[1]));
    }

}
