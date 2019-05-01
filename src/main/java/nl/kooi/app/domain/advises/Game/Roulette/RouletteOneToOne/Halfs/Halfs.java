package nl.kooi.app.domain.advises.Game.Roulette.RouletteOneToOne.Halfs;
import nl.kooi.app.domain.RouletteDomainObject;
import nl.kooi.app.domain.advises.Game.Roulette.RouletteOneToOne.RouletteOneToOne;
import nl.kooi.representation.Outcome;

/**
 * @author Laurens van der Kooi
 */

public class Halfs extends RouletteOneToOne {
    private Outcome outcome;
    private boolean[] hitArray = {true, true};
    private RouletteDomainObject roulette;

    public Halfs(double chipValue, char bettingSystem, int delay, RouletteDomainObject roulette) {
        super(chipValue, bettingSystem, delay, roulette);
    }
    @Override
    public void setHits(int outcome) {
         hitArray[0] = roulette.getHalf().equals(Outcome.FIRST);
        hitArray[1] = roulette.getHalf().equals(Outcome.SECOND);

    }


}
