package nl.kooi.app.domain.advises.Game.Roulette.RouletteTwoToOne;


import nl.kooi.app.domain.BettingSystem.TwoToOneBettingSystem;
import nl.kooi.app.domain.RouletteDomainObject;
import nl.kooi.representation.Outcome;
import nl.kooi.representation.advises.AdviceRepresentation;
import nl.kooi.representation.advises.DozenAdviceRepresentation;
import nl.kooi.representation.advises.RowAdviceRepresentation;

import java.math.BigDecimal;

/**
 * @author Laurens van der Kooi
 */

public class DozenGame extends RouletteTwoToOne {

    private boolean[] hitArray = {true, true, true};
    private RouletteDomainObject roulette;
    private TwoToOneBettingSystem bettingSystem;

    public DozenGame(String chipValue, char bettingSystem, RouletteDomainObject roulette) {
        super(chipValue, bettingSystem, roulette);
               }

    @Override
    public void setHits(int outcome){
        hitArray[0] = roulette.getDozen() == (Outcome.FIRST);
        hitArray[1] = roulette.getDozen() == (Outcome.SECOND);
        hitArray[2] = roulette.getDozen() == (Outcome.THIRD);
        this.setAdvice(hitArray);
    }

    @Override
    public DozenAdviceRepresentation toRepresentation() {
        int[] adviceArray = bettingSystem.getAdviceArray();
        DozenAdviceRepresentation representation = new DozenAdviceRepresentation();
        representation.lowDozen = getChipValue().multiply(new BigDecimal(adviceArray[0]));
        representation.midDozen = getChipValue().multiply(new BigDecimal(adviceArray[1]));
        representation.hiDozen = getChipValue().multiply(new BigDecimal(adviceArray[2]));
        return representation;
    }

}
