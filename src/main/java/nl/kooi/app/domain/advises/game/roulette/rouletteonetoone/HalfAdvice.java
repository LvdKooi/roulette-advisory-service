package nl.kooi.app.domain.advises.game.roulette.rouletteonetoone;

import nl.kooi.app.domain.RouletteDomainObject;
import nl.kooi.representation.RouletteOutcome;
import nl.kooi.representation.advises.HalfAdviceRepresentation;
import java.math.BigDecimal;

/**
 * @author Laurens van der Kooi
 */

public class HalfAdvice extends RouletteOneToOne {

    private boolean[] hitArray = {true, true};

    public HalfAdvice(String chipValue, char bettingSystem, int delay) {
        super(chipValue, bettingSystem, delay);
    }

    @Override
    public void setHits(RouletteDomainObject roulette) {
        hitArray[0] = roulette.getHalf().equals(RouletteOutcome.FIRST);
        hitArray[1] = roulette.getHalf().equals(RouletteOutcome.SECOND);
        this.setAdvice(hitArray);

    }

    @Override
    public HalfAdviceRepresentation toRepresentation() {
        int[] adviceArray = bettingSystem.getAdviceArray();
        HalfAdviceRepresentation representation = new HalfAdviceRepresentation();
        representation.firstHalf = getChipValue().multiply(new BigDecimal(adviceArray[0]));
        representation.secondHalf = getChipValue().multiply(new BigDecimal(adviceArray[1]));
        return representation;
    }
}
