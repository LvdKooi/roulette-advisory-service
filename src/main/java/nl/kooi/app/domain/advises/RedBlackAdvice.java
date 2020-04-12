package nl.kooi.app.domain.advises;

import nl.kooi.app.domain.rouletteoutcome.CompoundRouletteOutcome;
import nl.kooi.app.domain.rouletteoutcome.RouletteOutcome;
import nl.kooi.app.domain.game.RouletteOneToOne;
import nl.kooi.representation.advises.rouletteonetoone.RedBlackAdviceV1;
import java.math.BigDecimal;

/**
 * @author Laurens van der Kooi
 */

public class RedBlackAdvice extends RouletteOneToOne {
    private boolean[] hitArray = {true, true};

    public RedBlackAdvice(String chipValue, int delay) {
        super(chipValue, delay);
    }

    @Override
    public void setHits(CompoundRouletteOutcome roulette) {
        hitArray[0] = roulette.getOutcomeBooleanMap().get(RouletteOutcome.RED);
        hitArray[1] = roulette.getOutcomeBooleanMap().get(RouletteOutcome.BLACK);
        this.setAdvice(hitArray);
    }

    @Override
    public RedBlackAdviceV1 toRepresentationV1() {
        int[] adviceArray = bettingSystem.getAdviceArray();
        RedBlackAdviceV1 representation = new RedBlackAdviceV1();
        representation.red = getChipValue().multiply(new BigDecimal(adviceArray[0]));
        representation.black = getChipValue().multiply(new BigDecimal(adviceArray[1]));
        return representation;
    }
}
