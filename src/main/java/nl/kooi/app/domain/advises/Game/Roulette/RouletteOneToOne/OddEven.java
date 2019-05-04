package nl.kooi.app.domain.advises.Game.Roulette.RouletteOneToOne;


import nl.kooi.app.domain.BettingSystem.OneToOneBettingSystem;
import nl.kooi.app.domain.RouletteDomainObject;
import nl.kooi.representation.Outcome;
import nl.kooi.representation.advises.OddEvenAdviceRepresentation;
import java.math.BigDecimal;

/**
 * @author Laurens van der Kooi
 */

public class OddEven extends RouletteOneToOne {
    private boolean[] hitArray = {true, true};
    private OneToOneBettingSystem bettingSystem;

    public OddEven(String chipValue, char bettingSystem, int delay) {
        super(chipValue, bettingSystem, delay);
    }

    @Override
    public void setHits(RouletteDomainObject roulette) {
        hitArray[0] = roulette.getOddEven().equals(Outcome.ODD);
        hitArray[1] = roulette.getOddEven().equals(Outcome.EVEN);
    }

    @Override
    public OddEvenAdviceRepresentation toRepresentation() {
        int[] adviceArray = bettingSystem.getAdviceArray();
        OddEvenAdviceRepresentation representation = new OddEvenAdviceRepresentation();
        representation.odd = getChipValue().multiply(new BigDecimal(adviceArray[0]));
        representation.even = getChipValue().multiply(new BigDecimal(adviceArray[1]));
        return representation;
    }
}
