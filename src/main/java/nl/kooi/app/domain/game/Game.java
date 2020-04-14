package nl.kooi.app.domain.game;

import nl.kooi.app.domain.bettingsystem.BettingAdvice;
import nl.kooi.app.domain.outcome.Outcome;

import java.math.BigDecimal;

/**
 * @author Laurens van der Kooi
 */


public abstract class Game implements BettingAdvice {

    private BigDecimal chipValue;

    public Game(String chipValue) {
        this.chipValue = new BigDecimal(chipValue).setScale(2);
    }

    public void setChipValue(String chipValue) {
        this.chipValue = new BigDecimal(chipValue).setScale(2);
    }

    public BigDecimal getChipValue() {
        return chipValue;
    }

    public abstract String toString();

    public abstract void setHits(Outcome roulette);
}

