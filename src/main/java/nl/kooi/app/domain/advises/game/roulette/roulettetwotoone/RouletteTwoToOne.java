package nl.kooi.app.domain.advises.game.roulette.roulettetwotoone;


import nl.kooi.app.domain.bettingsystem.TwoToOneBettingSystem;
import nl.kooi.app.domain.advises.game.Game;

import java.math.BigDecimal;

/**
 * @author Laurens van der Kooi
 */

public abstract class RouletteTwoToOne extends Game {

    protected TwoToOneBettingSystem bettingSystem;

    public RouletteTwoToOne(String chipValue, char bettingSystem) {
        super(chipValue);
        this.bettingSystem = new TwoToOneBettingSystem(3, 4, bettingSystem);
    }

    @Override
    protected void setAdvice(boolean[] hitArray) {
        bettingSystem.compoundDefferedMartingGale(hitArray);

    }

    public void printAdvice() {
            for (int advice : bettingSystem.getAdviceArray())
            System.out.print(getChipValue().multiply(new BigDecimal(advice)) + "\t\t");
        System.out.println();
    }

    @Override
    public String toString() {
        return null;
    }

    @Override
    public BigDecimal getProfit() {
        System.out.println("profitCounter: " + bettingSystem.getProfitCounter());
        System.out.println("chipValue: " + getChipValue());
        System.out.println("profitFromAdviceObject: " +getChipValue().multiply(new BigDecimal(bettingSystem.getProfitCounter()).setScale(2)));

        return getChipValue().multiply(new BigDecimal(bettingSystem.getProfitCounter()).setScale(2));
    }

}