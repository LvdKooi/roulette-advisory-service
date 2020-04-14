package nl.kooi.app.domain.game;

import lombok.Getter;
import nl.kooi.app.domain.bettingsystem.BettingAdvice;
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
    }

    @Override
    public Map<RouletteOutcome, BigDecimal> getAdvice(boolean[] hitArray) {
        BettingAdvice.twoToOneBettingSystem.compoundDefferedMartingGale(hitArray);
        return BettingAdvice.getOneToOneAdviceMap(RED, BLACK, getChipValue());
    }
}
