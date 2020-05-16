package nl.kooi.app.domain.game;

import lombok.Getter;
import nl.kooi.app.domain.advice.Advice;
import nl.kooi.app.domain.outcome.Outcome;

import java.math.BigDecimal;

import static nl.kooi.app.domain.rouletteoutcome.RouletteOutcome.BLACK;
import static nl.kooi.app.domain.rouletteoutcome.RouletteOutcome.RED;

/**
 * @author Laurens van der Kooi
 */

@Getter
public class RedBlackGame extends RouletteOneToOne {
    private boolean[] hitArray = {true, true};

    public RedBlackGame(BigDecimal chipValue) {
        super(chipValue);
    }

    @Override
    public void setHits(Outcome roulette) {
        hitArray[0] = roulette.getRed();
        hitArray[1] = roulette.getBlack();
        getOneToOneBettingSystem().setHits(hitArray);
    }

    @Override
    public Advice getAdvice() {
        return getOneToOneBettingSystem().getOneToOneAdvice(RED, BLACK, getChipValue());
    }
}
