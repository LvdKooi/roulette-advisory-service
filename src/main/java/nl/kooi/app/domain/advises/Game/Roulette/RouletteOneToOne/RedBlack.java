package nl.kooi.app.domain.advises.Game.Roulette.RouletteOneToOne;

import nl.kooi.app.domain.BettingSystem.OneToOneBettingSystem;
import nl.kooi.app.domain.BettingSystem.TwoToOneBettingSystem;
import nl.kooi.app.domain.RouletteDomainObject;
import nl.kooi.representation.Outcome;
import nl.kooi.representation.advises.AdviceRepresentation;
import nl.kooi.representation.advises.DozenAdviceRepresentation;
import nl.kooi.representation.advises.RedBlackAdviceRepresentation;

import java.math.BigDecimal;

/**
 * @author Laurens van der Kooi
 */

public class RedBlack extends RouletteOneToOne {
    private boolean[] hitArray = {true, true};
    private RouletteDomainObject roulette;
    private OneToOneBettingSystem bettingSystem;

    public RedBlack(String chipValue, char bettingSystem, int delay, RouletteDomainObject roulette) {
        super(chipValue, bettingSystem, delay, roulette);
    }

    @Override
    public void setHits(int outcome) {
        hitArray[0] = roulette.getRedBlack().equals(Outcome.RED);
        hitArray[1] = roulette.getRedBlack().equals(Outcome.BLACK);

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
