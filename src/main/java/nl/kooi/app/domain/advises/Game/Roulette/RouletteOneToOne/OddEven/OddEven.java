package nl.kooi.app.domain.advises.Game.Roulette.RouletteOneToOne.OddEven;


import nl.kooi.app.domain.RouletteDomainObject;
import nl.kooi.app.domain.advises.Game.Roulette.RouletteOneToOne.RouletteOneToOne;
import nl.kooi.representation.Outcome;

/**
 * @author Laurens van der Kooi
 */

public class OddEven extends RouletteOneToOne {
    private Outcome outcome;
    private boolean[] hitArray = {true, true};
    private RouletteDomainObject roulette;

    public OddEven(double chipValue, char bettingSystem, int delay, RouletteDomainObject roulette) {
        super(chipValue, bettingSystem, delay, roulette);
    }

    public void setHits(int outcome) {
        hitArray[0] = roulette.getOddEven().equals(Outcome.ODD);
        hitArray[1] = roulette.getOddEven().equals(Outcome.EVEN);
    }

}
