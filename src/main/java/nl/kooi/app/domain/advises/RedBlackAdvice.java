package nl.kooi.app.domain.advises;

import lombok.Getter;
import nl.kooi.app.domain.game.RouletteOneToOne;
import nl.kooi.app.domain.outcome.Outcome;

import java.math.BigDecimal;

/**
 * @author Laurens van der Kooi
 */

@Getter
public class RedBlackAdvice extends RouletteOneToOne {
    private boolean[] hitArray = {true, true};
    private BigDecimal red;
    private BigDecimal black;


    public RedBlackAdvice(String chipValue, int delay) {
        super(chipValue, delay);
    }

    @Override
    public void setHits(Outcome roulette) {
        hitArray[0] = roulette.getRed();
        hitArray[1] = roulette.getBlack();
        this.setAdvice(hitArray);
    }

    @Override
    protected void setAdvice(boolean[] hitArray) {
        super.setAdvice(hitArray);
        red = getChipValue().multiply(new BigDecimal(bettingSystem.getAdviceArray()[0]));
        black = getChipValue().multiply(new BigDecimal(bettingSystem.getAdviceArray()[1]));
    }
}
