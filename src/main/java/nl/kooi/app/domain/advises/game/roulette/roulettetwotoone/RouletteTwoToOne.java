package nl.kooi.app.domain.advises.game.roulette.roulettetwotoone;


import nl.kooi.app.domain.advises.game.Game;
import nl.kooi.app.domain.bettingsystem.TwoToOneBettingSystem;

import java.math.BigDecimal;

/**
 * @author Laurens van der Kooi
 */

public abstract class RouletteTwoToOne extends Game {

    protected TwoToOneBettingSystem bettingSystem;

    public RouletteTwoToOne(String chipValue) {
        super(chipValue);
        this.bettingSystem = new TwoToOneBettingSystem(3, 4);
    }

    @Override
    protected void setAdvice(boolean[] hitArray) {
        bettingSystem.compoundDefferedMartingGale(hitArray);

    }

    @Override
    public String toString() {
        return null;
    }

    @Override
    public BigDecimal getProfit() {
        return getChipValue().multiply(new BigDecimal(bettingSystem.getProfitCounter()).setScale(2));
    }

}