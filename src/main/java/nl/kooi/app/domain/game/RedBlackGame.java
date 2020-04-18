package nl.kooi.app.domain.game;

import lombok.Getter;
import nl.kooi.app.domain.outcome.Outcome;
import nl.kooi.app.domain.rouletteoutcome.RouletteOutcome;

import java.math.BigDecimal;
import java.util.Map;

import static nl.kooi.app.domain.rouletteoutcome.RouletteOutcome.BLACK;
import static nl.kooi.app.domain.rouletteoutcome.RouletteOutcome.RED;

/**
 * @author Laurens van der Kooi
 */

@Getter
public class RedBlackGame extends RouletteOneToOne {
    private boolean[] hitArray = {true, true};

    public RedBlackGame(String chipValue) {
        super(chipValue);
    }

    @Override
    public void setHits(Outcome roulette) {
        hitArray[0] = roulette.getRed();
        hitArray[1] = roulette.getBlack();
        getOneToOneBettingSystem().compoundDefferedMartingGale(hitArray);
    }

    @Override
    public Map<RouletteOutcome, BigDecimal> getAdvice() {
        return getOneToOneBettingSystem().getOneToOneAdviceMap(RED, BLACK, getChipValue());
    }
}
