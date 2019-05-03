package nl.kooi.app.domain.advises.Game.Roulette.RouletteOneToOne;

import nl.kooi.app.domain.BettingSystem.OneToOneBettingSystem;
import nl.kooi.app.domain.RouletteDomainObject;
import nl.kooi.representation.Outcome;
import nl.kooi.representation.advises.AdviceRepresentation;
import nl.kooi.representation.advises.HalfAdviceRepresentation;
import nl.kooi.representation.advises.OddEvenAdviceRepresentation;
import nl.kooi.representation.advises.RowAdviceRepresentation;

import java.math.BigDecimal;

/**
 * @author Laurens van der Kooi
 */

public class Halfs extends RouletteOneToOne {

    private boolean[] hitArray = {true, true};
    private RouletteDomainObject roulette;
    private OneToOneBettingSystem bettingSystem;

    public Halfs(String chipValue, char bettingSystem, int delay, RouletteDomainObject roulette) {
        super(chipValue, bettingSystem, delay, roulette);
    }

    @Override
    public void setHits(int outcome) {
        hitArray[0] = roulette.getHalf().equals(Outcome.FIRST);
        hitArray[1] = roulette.getHalf().equals(Outcome.SECOND);

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
