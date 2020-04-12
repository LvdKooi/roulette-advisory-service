package nl.kooi.app.domain.advises;


import nl.kooi.app.domain.rouletteoutcome.CompoundRouletteOutcome;
import nl.kooi.app.domain.rouletteoutcome.RouletteOutcome;
import nl.kooi.app.domain.game.RouletteOneToOne;
import nl.kooi.app.api.dto.advises.OddEvenAdviceDto;
import java.math.BigDecimal;

/**
 * @author Laurens van der Kooi
 */

public class OddEvenAdvice extends RouletteOneToOne {
    private boolean[] hitArray = {true, true};

    public OddEvenAdvice(String chipValue, int delay) {
        super(chipValue, delay);

    }

    @Override
    public void setHits(CompoundRouletteOutcome roulette) {
        hitArray[0] = roulette.getOutcomeBooleanMap().get(RouletteOutcome.ODD);
        hitArray[1] = roulette.getOutcomeBooleanMap().get(RouletteOutcome.EVEN);
        this.setAdvice(hitArray);
    }

    @Override
    public OddEvenAdviceDto toRepresentationV1() {
        int[] adviceArray = bettingSystem.getAdviceArray();
        OddEvenAdviceDto representation = new OddEvenAdviceDto();
        representation.odd = getChipValue().multiply(new BigDecimal(adviceArray[0]));
        representation.even = getChipValue().multiply(new BigDecimal(adviceArray[1]));
        return representation;
    }
}
