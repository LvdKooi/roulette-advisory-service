package nl.kooi.app.domain.advises;


import nl.kooi.app.domain.rouletteoutcome.CompoundRouletteOutcome;
import nl.kooi.app.domain.rouletteoutcome.RouletteOutcome;
import nl.kooi.app.domain.game.RouletteTwoToOne;
import nl.kooi.app.api.dto.advises.DozenAdviceDto;


import java.math.BigDecimal;

/**
 * @author Laurens van der Kooi
 */

public class DozenAdvice extends RouletteTwoToOne {

    private boolean[] hitArray = {true, true, true};

    public DozenAdvice(String chipValue) {
        super(chipValue);
               }

    @Override
    public void setHits(CompoundRouletteOutcome roulette){
       hitArray[0] =  roulette.getOutcomeBooleanMap().get(RouletteOutcome.FIRST_DOZEN);
        hitArray[1] = roulette.getOutcomeBooleanMap().get(RouletteOutcome.SECOND_DOZEN);
        hitArray[2] = roulette.getOutcomeBooleanMap().get(RouletteOutcome.THIRD_DOZEN);
        this.setAdvice(hitArray);
    }

    @Override
    public DozenAdviceDto toRepresentationV1() {
        int[] adviceArray = bettingSystem.getAdviceArray();
        DozenAdviceDto representation = new DozenAdviceDto();
        representation.firstDozen = getChipValue().multiply(new BigDecimal(adviceArray[0]));
        representation.secondDozen = getChipValue().multiply(new BigDecimal(adviceArray[1]));
        representation.thirdDozen = getChipValue().multiply(new BigDecimal(adviceArray[2]));
        return representation;
    }

}
