package nl.kooi.app.domain.advises.game.roulette.rouletteonetoone;

import nl.kooi.app.domain.advises.game.Game;
import nl.kooi.app.domain.bettingsystem.OneToOneBettingSystem;

import java.math.BigDecimal;

/**
 * @author Laurens van der Kooi
 */

public abstract class RouletteOneToOne extends Game {
    protected OneToOneBettingSystem bettingSystem;

    public RouletteOneToOne(String chipValue, char bettingSystem, int delay) {
        super(chipValue);
        this.bettingSystem = new OneToOneBettingSystem(2, delay, bettingSystem);

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
