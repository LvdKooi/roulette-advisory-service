package nl.kooi.app.domain.advises;

import nl.kooi.app.domain.rouletteoutcome.CompoundRouletteOutcome;
import nl.kooi.app.domain.rouletteoutcome.RouletteOutcome;
import nl.kooi.app.domain.game.RouletteOneToOne;
import nl.kooi.representation.advises.rouletteonetoone.HalfAdviceV1;
import java.math.BigDecimal;

/**
 * @author Laurens van der Kooi
 */

public class HalfAdvice extends RouletteOneToOne {

    private boolean[] hitArray = {true, true};

    public HalfAdvice(String chipValue, int delay) {
        super(chipValue, delay);
    }

    @Override
    public void setHits(CompoundRouletteOutcome roulette) {
        hitArray[0] = roulette.getOutcomeBooleanMap().get(RouletteOutcome.FIRST_HALF);
        hitArray[1] = roulette.getOutcomeBooleanMap().get(RouletteOutcome.SECOND_HALF);
        this.setAdvice(hitArray);

    }

    @Override
    public HalfAdviceV1 toRepresentationV1() {
        int[] adviceArray = bettingSystem.getAdviceArray();
        HalfAdviceV1 representation = new HalfAdviceV1();
        representation.firstHalf = getChipValue().multiply(new BigDecimal(adviceArray[0]));
        representation.secondHalf = getChipValue().multiply(new BigDecimal(adviceArray[1]));
        return representation;
    }
}
