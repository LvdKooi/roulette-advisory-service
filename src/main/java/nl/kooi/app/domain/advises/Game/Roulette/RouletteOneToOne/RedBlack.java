package nl.kooi.app.domain.advises.Game.Roulette.RouletteOneToOne;

import nl.kooi.app.domain.RouletteDomainObject;
import nl.kooi.representation.Outcome;
import nl.kooi.representation.advises.AdviceRepresentation;

/**
 * @author Laurens van der Kooi
 */

public class RedBlack extends RouletteOneToOne {
    private boolean[] hitArray = {true, true};
    private RouletteDomainObject roulette;

    public RedBlack(String chipValue, char bettingSystem, int delay, RouletteDomainObject roulette) {
        super(chipValue, bettingSystem, delay, roulette);
    }

    @Override
    public void setHits(int outcome) {
        hitArray[0] = roulette.getRedBlack().equals(Outcome.RED);
        hitArray[1] = roulette.getRedBlack().equals(Outcome.BLACK);

    }

    @Override
    public AdviceRepresentation toRepresentation() {
        return null;
    }
}
