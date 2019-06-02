package nl.kooi.app.domain.bettingsystem;

/**
 * @author Laurens van der Kooi
 */

public class BettingSystem {

    private final int BETTING_FACTOR, DELAY;
    private int rounds, mFactor, winCount, lossCount, advice;
    private int totalRounds;
    private char system;
    private boolean[] outcomeArray = {true, true, true, true};
    private int profitCounter;

    public BettingSystem(int bettingFactor, int delay, char system) {
        this.BETTING_FACTOR = bettingFactor;
        this.DELAY = delay;
        this.system = system;
        rounds = 1;
        totalRounds = 1;
        mFactor = 0;
    }

    public BettingSystem(int bettingFactor) {
        this(bettingFactor, 0, 'M');
    }

    public int getAdvice(boolean isHit) {


        switch (system) {
            case 'M':
                return martingale(isHit);
            case 'D':
                return defferedMartingale(isHit);
        }

        return 0;
    }

    protected int martingale(boolean isHit) {
        if (isHit) {
            return mFactor = 0;

        } else {
            return mFactor = mFactor == 0 ? 1 : mFactor * BETTING_FACTOR;

        }
    }

    private int defferedMartingale(boolean isHit) {
        winCount = 0;
        lossCount = 0;

        outcomeArray[rounds - 1] = isHit;

        if (rounds < DELAY) {
            rounds++;
        } else {
            rounds = 1;
        }

        for (boolean outcome : outcomeArray) {
            if (outcome) {
                winCount++;
            } else {
                lossCount++;
            }
        }

        if (mFactor > 0 && !isHit) {
            return mFactor *= getBettingFactor();
        } else if (lossCount >= getDelay() - 1 && isHit) {
            return mFactor = 1;
        } else if (lossCount == getDelay() && !isHit) {
            return mFactor = Math.max(1, mFactor *= getBettingFactor());
        } else {
            return mFactor = 0;

        }
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

    public void setMFactor(int mFactor) {
        this.mFactor = mFactor;
    }

    public int getMFactor() {
        return this.mFactor;
    }

    public int getDelay() {
        return DELAY;
    }

    public int getLossCount() {
        return lossCount;
    }

    public void setLossCount(int lossCount) {
        this.lossCount = lossCount;
    }

    public int getWinCount() {
        return winCount;
    }

    public void setWinCount(int winCount) {
        this.winCount = winCount;
    }

    public int getBettingFactor() {
        return this.BETTING_FACTOR;
    }

    private void setOutcomeArray(boolean[] outcomeArray) {
        this.outcomeArray = outcomeArray;
    }

    public BettingSystem copy() {
        BettingSystem copy = new BettingSystem(this.BETTING_FACTOR, this.DELAY, this.system);
        copy.setRounds(this.rounds);
        copy.setMFactor(this.mFactor);
        copy.setOutcomeArray(this.outcomeArray);
        copy.setWinCount(this.winCount);
        copy.setLossCount(this.lossCount);
        return copy;
    }

    public int getProfitCounter() {
        return profitCounter;
    }

}
