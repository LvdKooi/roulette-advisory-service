package nl.kooi.app.domain.advises.game.roulette.rouletteonetoone;

import nl.kooi.app.domain.CompoundRouletteOutcome;
import nl.kooi.app.domain.RouletteOutcome;
import nl.kooi.representation.advises.rouletteonetoone.HalfAdviceV1;
import java.math.BigDecimal;

/**
 * @author Laurens van der Kooi
 */

public class HalfAdvice extends RouletteOneToOne {

    private boolean[] hitArray = {true, true};

    public HalfAdvice(String chipValue, char bettingSystem, int delay) {
        super(chipValue, bettingSystem, delay);
    }

    @Override
    public void setHits(CompoundRouletteOutcome roulette) {
        hitArray[0] = roulette.getHalf().equals(RouletteOutcome.FIRST);
        hitArray[1] = roulette.getHalf().equals(RouletteOutcome.SECOND);
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
