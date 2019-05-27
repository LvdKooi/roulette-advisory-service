package nl.kooi.app.domain.advises.Game.Roulette.RouletteTwoToOne;


import nl.kooi.app.domain.RouletteDomainObject;
import nl.kooi.representation.RouletteOutcome;
import nl.kooi.representation.advises.DozenAdviceRepresentation;


import java.math.BigDecimal;

/**
 * @author Laurens van der Kooi
 */

public class DozenAdvice extends RouletteTwoToOne {

    private boolean[] hitArray = {true, true, true};

    public DozenAdvice(String chipValue, char bettingSystem) {
        super(chipValue, bettingSystem);
               }

    @Override
    public void setHits(RouletteDomainObject roulette){
       hitArray[0] = roulette.getDozen().equals(RouletteOutcome.FIRST);
        hitArray[1] = roulette.getDozen().equals(RouletteOutcome.SECOND);
        hitArray[2] = roulette.getDozen().equals(RouletteOutcome.THIRD);
        this.setAdvice(hitArray);
    }

    @Override
    public DozenAdviceRepresentation toRepresentation() {
        int[] adviceArray = bettingSystem.getAdviceArray();
        DozenAdviceRepresentation representation = new DozenAdviceRepresentation();
        representation.firstDozen = getChipValue().multiply(new BigDecimal(adviceArray[0]));
        representation.secondDozen = getChipValue().multiply(new BigDecimal(adviceArray[1]));
        representation.thirdDozen = getChipValue().multiply(new BigDecimal(adviceArray[2]));
        return representation;
    }

}
