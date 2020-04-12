package nl.kooi.app.domain.game;

import nl.kooi.app.domain.rouletteoutcome.CompoundRouletteOutcome;
import nl.kooi.representation.advises.AdviceRepresentation;
import java.math.BigDecimal;

/**
 * @author Laurens van der Kooi
 */


public abstract class Game {

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

    public abstract void setHits(CompoundRouletteOutcome roulette);

    protected abstract void setAdvice(boolean[] hitArray);

    public abstract AdviceRepresentation toRepresentationV1();

    public abstract BigDecimal getProfit();
}

