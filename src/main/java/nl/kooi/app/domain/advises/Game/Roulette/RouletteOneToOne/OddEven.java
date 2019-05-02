package nl.kooi.app.domain.advises.Game.Roulette.RouletteOneToOne;


import nl.kooi.app.domain.RouletteDomainObject;
import nl.kooi.representation.Outcome;
import nl.kooi.representation.advises.AdviceRepresentation;

/**
 * @author Laurens van der Kooi
 */

public class OddEven extends RouletteOneToOne {
    private boolean[] hitArray = {true, true};
    private RouletteDomainObject roulette;

    public OddEven(String chipValue, char bettingSystem, int delay, RouletteDomainObject roulette) {
        super(chipValue, bettingSystem, delay, roulette);
    }

    public void setHits(int outcome) {
        hitArray[0] = roulette.getOddEven().equals(Outcome.ODD);
        hitArray[1] = roulette.getOddEven().equals(Outcome.EVEN);
    }

    @Override
    public AdviceRepresentation toRepresentation() {
        return null;
    }
}
