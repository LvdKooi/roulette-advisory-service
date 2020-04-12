package nl.kooi.app.domain.advises;


import lombok.Getter;
import nl.kooi.app.domain.game.RouletteOneToOne;
import nl.kooi.app.domain.rouletteoutcome.CompoundRouletteOutcome;

import java.math.BigDecimal;

import static nl.kooi.app.domain.rouletteoutcome.RouletteOutcome.EVEN;
import static nl.kooi.app.domain.rouletteoutcome.RouletteOutcome.ODD;

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
    public void setHits(CompoundRouletteOutcome roulette) {
        hitArray[0] = roulette.getOutcomeBooleanMap().get(ODD);
        hitArray[1] = roulette.getOutcomeBooleanMap().get(EVEN);
        this.setAdvice(hitArray);
    }

    @Override
    protected void setAdvice(boolean[] hitArray) {
        super.setAdvice(hitArray);
        odd = getChipValue().multiply(new BigDecimal(bettingSystem.getAdviceArray()[0]));
        even = getChipValue().multiply(new BigDecimal(bettingSystem.getAdviceArray()[1]));
    }

}
