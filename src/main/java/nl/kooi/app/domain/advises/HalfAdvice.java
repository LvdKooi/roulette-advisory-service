package nl.kooi.app.domain.advises;

import nl.kooi.app.domain.rouletteoutcome.CompoundRouletteOutcome;
import nl.kooi.app.domain.rouletteoutcome.RouletteOutcome;
import nl.kooi.app.domain.game.RouletteOneToOne;
import nl.kooi.app.api.dto.advises.HalfAdviceDto;
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
    public HalfAdviceDto toRepresentationV1() {
        int[] adviceArray = bettingSystem.getAdviceArray();
        HalfAdviceDto representation = new HalfAdviceDto();
        representation.firstHalf = getChipValue().multiply(new BigDecimal(adviceArray[0]));
        representation.secondHalf = getChipValue().multiply(new BigDecimal(adviceArray[1]));
        return representation;
    }
}
