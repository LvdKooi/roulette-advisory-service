package nl.kooi.app.domain.advises.Game.Roulette.RouletteOneToOne;

import nl.kooi.app.domain.BettingSystem.OneToOneBettingSystem;
import nl.kooi.app.domain.RouletteDomainObject;
import nl.kooi.app.domain.advises.Game.Game;

import java.math.BigDecimal;

/**
 * @author Laurens van der Kooi
 */

public abstract class RouletteOneToOne extends Game {
    private OneToOneBettingSystem bettingSystem;
    private RouletteDomainObject roulette;

    public RouletteOneToOne(String chipValue, char bettingSystem, int delay, RouletteDomainObject roulette) {
        super(chipValue, bettingSystem);
        this.bettingSystem = new OneToOneBettingSystem(2, delay, getBettingSystem());
        this.roulette = roulette;
    }

    @Override
    protected void setAdvice(boolean[] hitArray) {
        bettingSystem.compoundDefferedMartingGale(hitArray);

    }

    public void printAdvice() {

        for(int advice : bettingSystem.getAdviceArray())
            System.out.print(getChipValue().multiply(new BigDecimal(advice)) + "\t\t");
        System.out.println();
    }

    @Override
    public String toString() {
        return null;
    }



}
