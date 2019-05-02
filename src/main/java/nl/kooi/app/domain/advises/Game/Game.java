package nl.kooi.app.domain.advises.Game;

import nl.kooi.representation.advises.AdviceRepresentation;
import nl.kooi.representation.advises.RowAdviceRepresentation;

import java.math.BigDecimal;

/**
 * @author Laurens van der Kooi
 */

public abstract class Game {

    private BigDecimal chipValue;
    private char bettingSystem;

    public Game(String chipValue, char bettingSystem) {
        this.chipValue = new BigDecimal(chipValue).setScale(2);
        this.bettingSystem = bettingSystem;
    }

    public void setChipValue(String chipValue) {
        this.chipValue = new BigDecimal(chipValue).setScale(2);
    }


    public BigDecimal getChipValue() {
        return chipValue;
    }

    public char getBettingSystem() {
        return bettingSystem;
    }

    public abstract String toString();

    public abstract void setHits(int outcome);

    protected abstract void setAdvice(boolean[] hitArray);

    public abstract AdviceRepresentation toRepresentation();
}

