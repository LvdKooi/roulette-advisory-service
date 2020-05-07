package nl.kooi.app.domain.game;

import lombok.Getter;
import nl.kooi.app.domain.bettingsystem.TwoToOneBettingSystem;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Getter
public abstract class RouletteTwoToOne extends Game {
    private TwoToOneBettingSystem twoToOneBettingSystem = new TwoToOneBettingSystem(3, 4);

    public RouletteTwoToOne(BigDecimal chipValue) { super(chipValue); }

    @Override
    public String toString() {
        return null;
    }

    @Override
    public BigDecimal getProfit() {
        return getChipValue().multiply(new BigDecimal(twoToOneBettingSystem.getProfitCounter()).setScale(2, RoundingMode.HALF_UP));
    }

}