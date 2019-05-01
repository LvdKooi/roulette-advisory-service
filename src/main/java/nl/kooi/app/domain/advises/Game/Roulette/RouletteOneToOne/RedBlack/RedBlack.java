package nl.kooi.app.domain.advises.Game.Roulette.RouletteOneToOne.RedBlack;

import nl.kooi.app.domain.RouletteDomainObject;
import nl.kooi.app.domain.advises.Game.Roulette.RouletteOneToOne.RouletteOneToOne;
import nl.kooi.representation.Outcome;

/**
 * @author Laurens van der Kooi
 */

public class RedBlack extends RouletteOneToOne {
    private Outcome outcome;
    private boolean[] hitArray = {true, true};
    private RouletteDomainObject roulette;

    public RedBlack(double chipValue, char bettingSystem, int delay, RouletteDomainObject roulette) {
        super(chipValue, bettingSystem, delay, roulette);
    }

    @Override
    public void setHits(int outcome) {
        hitArray[0] = roulette.getRedBlack().equals(Outcome.RED);
        hitArray[1] = roulette.getRedBlack().equals(Outcome.BLACK);

    }


}
