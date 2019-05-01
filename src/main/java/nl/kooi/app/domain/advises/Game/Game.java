package nl.kooi.app.domain.advises.Game;

/**
 * @author Laurens van der Kooi
 */

public abstract class Game {

    private double chipValue;
    private char bettingSystem;

    public Game(double chipValue, char bettingSystem) {
        this.chipValue = chipValue;
        this.bettingSystem = bettingSystem;
    }

    public void setChipValue(double chipValue) {
        this.chipValue = chipValue;
    }


    public double getChipValue() {
        return chipValue;
    }

    public char getBettingSystem() {
        return bettingSystem; }

    public abstract String toString();

    public abstract void setHits(int outcome);

    protected abstract void setAdvice(boolean[] hitArray);


}
