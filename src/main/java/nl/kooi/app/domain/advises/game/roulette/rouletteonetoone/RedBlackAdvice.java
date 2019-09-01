package nl.kooi.app.domain.advises.game.roulette.rouletteonetoone;

import nl.kooi.app.domain.CompoundRouletteOutcomeObject;
import nl.kooi.representation.RouletteOutcome;
import nl.kooi.representation.advises.RedBlackAdviceRepresentation;
import java.math.BigDecimal;

/**
 * @author Laurens van der Kooi
 */

public class RedBlackAdvice extends RouletteOneToOne {
    private boolean[] hitArray = {true, true};

    public RedBlackAdvice(String chipValue, char bettingSystem, int delay) {
        super(chipValue, bettingSystem, delay);
    }

    @Override
    public void setHits(CompoundRouletteOutcomeObject roulette) {
        hitArray[0] = roulette.getRedBlack().equals(RouletteOutcome.RED);
        hitArray[1] = roulette.getRedBlack().equals(RouletteOutcome.BLACK);
        this.setAdvice(hitArray);
    }

    @Override
    public RedBlackAdviceRepresentation toRepresentation() {
        int[] adviceArray = bettingSystem.getAdviceArray();
        RedBlackAdviceRepresentation representation = new RedBlackAdviceRepresentation();
        representation.red = getChipValue().multiply(new BigDecimal(adviceArray[0]));
        representation.black = getChipValue().multiply(new BigDecimal(adviceArray[1]));
        return representation;
    }
}
