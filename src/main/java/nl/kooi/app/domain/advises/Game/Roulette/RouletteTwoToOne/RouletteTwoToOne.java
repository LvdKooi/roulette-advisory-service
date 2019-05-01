package nl.kooi.app.domain.advises.Game.Roulette.RouletteTwoToOne;


import nl.kooi.app.domain.BettingSystem.TwoToOneBettingSystem;
import nl.kooi.app.domain.RouletteDomainObject;
import nl.kooi.app.domain.advises.Game.Game;

/**
 * @author Laurens van der Kooi
 */

public abstract class RouletteTwoToOne extends Game {

    private TwoToOneBettingSystem bettingSystem;
    private RouletteDomainObject roulette;

    public RouletteTwoToOne(double chipValue, char bettingSystem, RouletteDomainObject roulette) {
        super(chipValue, bettingSystem);
        this.bettingSystem = new TwoToOneBettingSystem(3, 4, getBettingSystem());
        this.roulette = roulette;
    }

    @Override
    protected void setAdvice(boolean[] hitArray) {
        bettingSystem.compoundDefferedMartingGale(hitArray);

    }

    public void printAdvice() {
            for (int advice : bettingSystem.getAdviceArray())
            System.out.print(advice * getChipValue() + "\t\t");
        System.out.println();
    }

    @Override
    public String toString() {
        return null;
    }

}
