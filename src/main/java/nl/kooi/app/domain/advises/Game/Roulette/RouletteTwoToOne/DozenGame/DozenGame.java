package nl.kooi.app.domain.advises.Game.Roulette.RouletteTwoToOne.DozenGame;


import nl.kooi.app.domain.RouletteDomainObject;
import nl.kooi.app.domain.advises.Game.Roulette.RouletteTwoToOne.RouletteTwoToOne;
import nl.kooi.representation.Outcome;

/**
 * @author Laurens van der Kooi
 */

public class DozenGame extends RouletteTwoToOne {

    private boolean[] hitArray = {true, true, true};
    private Outcome outcome;
    private RouletteDomainObject roulette;

    public DozenGame(double chipValue, char bettingSystem, RouletteDomainObject roulette) {
        super(chipValue, bettingSystem, roulette);
               }

    @Override
    public void setHits(int outcome){
        hitArray[0] = roulette.getDozen() == (Outcome.FIRST);
        hitArray[1] = roulette.getDozen() == (Outcome.SECOND);
        hitArray[2] = roulette.getDozen() == (Outcome.THIRD);
        this.setAdvice(hitArray);
    }

}
