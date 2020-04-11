package nl.kooi.app.domain.advises.game.roulette.rouletteonetoone;


import nl.kooi.app.domain.CompoundRouletteOutcome;
import nl.kooi.app.domain.RouletteOutcome;
import nl.kooi.representation.advises.rouletteonetoone.OddEvenAdviceV1;
import java.math.BigDecimal;

/**
 * @author Laurens van der Kooi
 */

public class OddEvenAdvice extends RouletteOneToOne {
    private boolean[] hitArray = {true, true};

    public OddEvenAdvice(String chipValue, char bettingSystem, int delay) {
        super(chipValue, bettingSystem, delay);

    }

    @Override
    public void setHits(CompoundRouletteOutcome roulette) {
        hitArray[0] = roulette.getOutcomeBooleanMap().get(RouletteOutcome.ODD);
        hitArray[1] = roulette.getOutcomeBooleanMap().get(RouletteOutcome.EVEN);
        this.setAdvice(hitArray);
    }

    @Override
    public OddEvenAdviceV1 toRepresentationV1() {
        int[] adviceArray = bettingSystem.getAdviceArray();
        OddEvenAdviceV1 representation = new OddEvenAdviceV1();
        representation.odd = getChipValue().multiply(new BigDecimal(adviceArray[0]));
        representation.even = getChipValue().multiply(new BigDecimal(adviceArray[1]));
        return representation;
    }
}
