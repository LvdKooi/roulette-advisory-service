package nl.kooi.app.domain.bettingsystem;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Laurens van der Kooi
 */

@Getter
@Setter
public abstract class BettingSystem {

    private int bettingFactor;
    private int delay;
    private int rounds;
    private int totalRounds;
    private boolean[] outcomeArray = {true, true, true, true};

    public BettingSystem(int bettingFactor, int delay) {
        this.bettingFactor = bettingFactor;
        this.delay = delay;
        rounds = 1;
        totalRounds = 1;
    }

    public abstract int getProfitCounter();

    public abstract void setHits(boolean[] hitArray);

}
