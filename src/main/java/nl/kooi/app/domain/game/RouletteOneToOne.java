package nl.kooi.app.domain.game;

import nl.kooi.app.domain.bettingsystem.BettingAdvice;

import java.math.BigDecimal;

/**
 * @author Laurens van der Kooi
 */

public abstract class RouletteOneToOne extends Game {

    public RouletteOneToOne(String chipValue) {
        super(chipValue);
    }

    @Override
    public String toString() {
        return null;
    }

    @Override
    public BigDecimal getProfit() {
        return getChipValue().multiply(new BigDecimal(BettingAdvice.oneToOneBettingSystem.getProfitCounter()).setScale(2));
    }
}
