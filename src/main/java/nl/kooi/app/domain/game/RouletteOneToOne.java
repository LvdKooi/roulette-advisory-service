package nl.kooi.app.domain.game;

import lombok.Getter;
import nl.kooi.app.domain.bettingsystem.OneToOneBettingSystem;

import java.math.BigDecimal;

/**
 * @author Laurens van der Kooi
 */

@Getter
public abstract class RouletteOneToOne extends Game {
    private OneToOneBettingSystem oneToOneBettingSystem = new OneToOneBettingSystem(2, 8);

    public RouletteOneToOne(BigDecimal chipValue) {
        super(chipValue);
    }

    @Override
    public String toString() {
        return null;
    }

    @Override
    public BigDecimal getProfit() {
        return getChipValue().multiply(new BigDecimal(oneToOneBettingSystem.getProfitCounter()).setScale(2));
    }
}
