package nl.kooi.app.domain.advises;

import nl.kooi.app.domain.rouletteoutcome.CompoundRouletteOutcome;
import nl.kooi.app.domain.rouletteoutcome.RouletteOutcome;
import nl.kooi.app.domain.game.RouletteOneToOne;
import nl.kooi.app.api.dto.advises.RedBlackAdviceDto;
import java.math.BigDecimal;

/**
 * @author Laurens van der Kooi
 */

public class RedBlackAdvice extends RouletteOneToOne {
    private boolean[] hitArray = {true, true};

    public RedBlackAdvice(String chipValue, int delay) {
        super(chipValue, delay);
    }

    @Override
    public void setHits(CompoundRouletteOutcome roulette) {
        hitArray[0] = roulette.getOutcomeBooleanMap().get(RouletteOutcome.RED);
        hitArray[1] = roulette.getOutcomeBooleanMap().get(RouletteOutcome.BLACK);
        this.setAdvice(hitArray);
    }

    @Override
    public RedBlackAdviceDto toRepresentationV1() {
        int[] adviceArray = bettingSystem.getAdviceArray();
        RedBlackAdviceDto representation = new RedBlackAdviceDto();
        representation.red = getChipValue().multiply(new BigDecimal(adviceArray[0]));
        representation.black = getChipValue().multiply(new BigDecimal(adviceArray[1]));
        return representation;
    }
}
