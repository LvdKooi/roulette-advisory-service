package nl.kooi.app.domain.advises.game.roulette.roulettetwotoone;


import nl.kooi.app.domain.CompoundRouletteOutcome;
import nl.kooi.app.domain.RouletteOutcome;
import nl.kooi.representation.advises.roulettetwotoone.DozenAdviceV1;


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
    public void setHits(CompoundRouletteOutcome roulette){
       hitArray[0] = roulette.getDozen().equals(RouletteOutcome.FIRST);
        hitArray[1] = roulette.getDozen().equals(RouletteOutcome.SECOND);
        hitArray[2] = roulette.getDozen().equals(RouletteOutcome.THIRD);
        this.setAdvice(hitArray);
    }

    @Override
    public DozenAdviceV1 toRepresentationV1() {
        int[] adviceArray = bettingSystem.getAdviceArray();
        DozenAdviceV1 representation = new DozenAdviceV1();
        representation.firstDozen = getChipValue().multiply(new BigDecimal(adviceArray[0]));
        representation.secondDozen = getChipValue().multiply(new BigDecimal(adviceArray[1]));
        representation.thirdDozen = getChipValue().multiply(new BigDecimal(adviceArray[2]));
        return representation;
    }

}
