package nl.kooi.app.domain.bettingsystem;

/**
 * @author Laurens van der Kooi
 */

public class BettingSystem {

    private final int BETTING_FACTOR, DELAY;
    private int rounds;
    private int totalRounds;
    private boolean[] outcomeArray = {true, true, true, true};
    private int profitCounter;

    public BettingSystem(int bettingFactor, int delay) {
        this.BETTING_FACTOR = bettingFactor;
        this.DELAY = delay;
         rounds = 1;
        totalRounds = 1;
      }

    public BettingSystem(int bettingFactor) {
        this(bettingFactor, 0);
    }

    public int getRounds() {
        return rounds;
    }

    public void setRounds(int rounds) {
        this.rounds = rounds;
    }

    public int getTotalRounds() {
        return totalRounds;
    }

    public  void setTotalRounds(int totalRounds) {
        this.totalRounds = totalRounds;
    }

    public int getDelay() {
        return DELAY;
    }


    public int getBettingFactor() {
        return this.BETTING_FACTOR;
    }

    private void setOutcomeArray(boolean[] outcomeArray) {
        this.outcomeArray = outcomeArray;
    }

    public int getProfitCounter() {
        return profitCounter;
    }

}
