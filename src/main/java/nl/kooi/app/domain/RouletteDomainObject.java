package nl.kooi.app.domain;

import nl.kooi.app.exceptions.notValidOutcomeException;
import nl.kooi.representation.Outcome;
import nl.kooi.representation.RouletteRepresentationObject;

import java.util.Arrays;

/**
 * @author Laurens van der Kooi
 */

public class RouletteDomainObject {

    private static final int[] FIRST_ROW;
    private static final int[] SECOND_ROW;
    private static final int[] THIRD_ROW;
    private static final int[] RED_NUMBERS;
    private static final int[] BLACK_NUMBERS;
    private int outcome;
    private Outcome redBlack;
    private Outcome oddEven;
    private Outcome half;
    private Outcome row;
    private Outcome dozen;
    private boolean isZero;

    public RouletteDomainObject(int outcome) {
        validOutcomeCheck(outcome);
        this.outcome = outcome;
        this.redBlack = redBlack(outcome);
        this.oddEven = oddEven(outcome);
        this.half = half(outcome);
        this.row = row(outcome);
        this.dozen = dozen(outcome);
        this.isZero = isZero(outcome);
    }

    static {
        FIRST_ROW = new int[12];
        SECOND_ROW = new int[12];
        THIRD_ROW = new int[12];
        RED_NUMBERS = new int[18];
        BLACK_NUMBERS = new int[18];

        // populating rows
        for (int i = 1, j = 0; i < 37; i += 3, j++) {
            FIRST_ROW[j] = i;
            SECOND_ROW[j] = i + 1;
            THIRD_ROW[j] = i + 2;
        }

        //populating red + black
        for (int i = 1, j = 0; i < 10; i += 2, j++) {
            RED_NUMBERS[j] = i;
            BLACK_NUMBERS[j] = i + 1;
        }
        for (int i = 12, j = 5; i < 19; i += 2, j++) {
            RED_NUMBERS[j] = i;
            BLACK_NUMBERS[j] = i - 1;
        }
        for (int i = 19, j = 9; i < 28; i += 2, j++) {
            RED_NUMBERS[j] = i;
            BLACK_NUMBERS[j] = i + 1;
        }

        for (int i = 30, j = 14; i < 37; i += 2, j++) {
            RED_NUMBERS[j] = i;
            BLACK_NUMBERS[j] = i - 1;
        }
    }

    private static void validOutcomeCheck(int outcome) {
        if (!(outcome > -1 && outcome < 37)) {
            throw new notValidOutcomeException("Not a valid Roulette outcome. Outcome can only be within the range of 0 - 36.");
        }
    }

    public void setOutcome(int outcome) {
        validOutcomeCheck(outcome);
        this.outcome = outcome;
        this.redBlack = redBlack(outcome);
        this.oddEven = oddEven(outcome);
        this.half = half(outcome);
        this.row = row(outcome);
        this.dozen = dozen(outcome);
        this.isZero = isZero(outcome);
    }

    public int getOutcome() {
        return outcome;
    }

    public Outcome getRedBlack() {
        return redBlack;
    }

    public Outcome getOddEven() {
        return oddEven;
    }

    public Outcome getHalf() {
        return half;
    }

    public Outcome getRow() {
        return row;
    }

    public Outcome getDozen() {
        return dozen;
    }

    public boolean isZero() {
        return isZero;
    }

    private static Outcome dozen(int currentInput) {
        if (currentInput > 0 && currentInput < 13) {
            return Outcome.FIRST;
        } else if (currentInput > 12 && currentInput < 25) {
            return Outcome.SECOND;
        } else if (currentInput > 24 && currentInput < 37) {
            return Outcome.THIRD;
        } else {
            return Outcome.ZERO;
        }
    }

    private static Outcome row(int currentInput) {
        if (Arrays.binarySearch(FIRST_ROW, currentInput) > -1) {
            return Outcome.LOW;
        } else if (Arrays.binarySearch(SECOND_ROW, currentInput) > -1) {
            return Outcome.MID;
        } else if (Arrays.binarySearch(THIRD_ROW, currentInput) > -1) {
            return Outcome.HI;
        } else {
            return Outcome.ZERO;
        }
    }

    private static Outcome redBlack(int currentInput) {
        if (Arrays.binarySearch(RED_NUMBERS, currentInput) > -1) {
            return Outcome.RED;
        } else if (Arrays.binarySearch(BLACK_NUMBERS, currentInput) > -1) {
            return Outcome.BLACK;
        } else {
            return Outcome.ZERO;
        }
    }

    private static Outcome oddEven(int currentInput) {
        if (isZero(currentInput)) {
            return Outcome.ZERO;
        } else {
            return currentInput % 2 == 0 ? Outcome.EVEN : Outcome.ODD;
        }
    }

    private static Outcome half(int currentInput) {
        if (isZero(currentInput)) {
            return Outcome.ZERO;
        } else {
            return currentInput < 19 ? Outcome.FIRST : Outcome.SECOND;
        }
    }

    private static boolean isZero(int currentInput) {
        return currentInput == 0;
    }

    @Override
    public String toString() {
        return
                "OUTCOME: " + this.outcome +
                        " DOZEN: " + this.dozen +
                        " ROW: " + this.row +
                        " BLACK/RED: " + this.redBlack +
                        " ODD/EVEN: " + this.oddEven +
                        " HALFS: " + this.half + " ";
    }

    public RouletteRepresentationObject toRepresentation() {
        RouletteRepresentationObject representation = new RouletteRepresentationObject();
        representation.dozen = this.dozen;
        representation.half = this.half;
        representation.isZero = this.isZero;
        representation.oddEven = this.oddEven;
        representation.redBlack = this.redBlack;
        representation.outcome = this.outcome;
        representation.row = this.row;
        return representation;
    }

}
