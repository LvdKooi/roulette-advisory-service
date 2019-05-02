package nl.kooi.app.domain.advises.Game.Roulette.RouletteOneToOne;

import nl.kooi.app.domain.RouletteDomainObject;
import nl.kooi.representation.Outcome;
import nl.kooi.representation.advises.AdviceRepresentation;
import nl.kooi.representation.advises.RowAdviceRepresentation;

/**
 * @author Laurens van der Kooi
 */

public class Halfs extends RouletteOneToOne {

    private boolean[] hitArray = {true, true};
    private RouletteDomainObject roulette;

    public Halfs(String chipValue, char bettingSystem, int delay, RouletteDomainObject roulette) {
        super(chipValue, bettingSystem, delay, roulette);
    }

    @Override
    public void setHits(int outcome) {
        hitArray[0] = roulette.getHalf().equals(Outcome.FIRST);
        hitArray[1] = roulette.getHalf().equals(Outcome.SECOND);

    }

    @Override
    public AdviceRepresentation toRepresentation() {
        return null;
    }
}
