package nl.kooi.app.domain.game;

import nl.kooi.app.domain.advice.Advice;
import nl.kooi.app.domain.outcome.Outcome;
import nl.kooi.app.domain.rouletteoutcome.RouletteOutcome;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;

/**
 * @author Laurens van der Kooi
 */


public abstract class Game {

    private BigDecimal chipValue;

    public Game(BigDecimal chipValue) {
        this.chipValue = chipValue.setScale(2, RoundingMode.HALF_UP);
    }

    public void setChipValue(String chipValue) {
        this.chipValue = new BigDecimal(chipValue).setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal getChipValue() {
        return chipValue;
    }

    public abstract String toString();

    public abstract void setHits(Outcome roulette);

    public abstract BigDecimal getProfit();

    public abstract Advice getAdvice();
}

