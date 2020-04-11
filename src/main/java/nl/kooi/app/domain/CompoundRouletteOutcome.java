package nl.kooi.app.domain;

import nl.kooi.app.exceptions.NotValidOutcomeException;
import nl.kooi.representation.CompoundRouletteOutcomeV1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Laurens van der Kooi
 */

public class CompoundRouletteOutcome {

    private static final List<Integer> FIRST_COLUMN;
    private static final List<Integer> SECOND_COLUMN;
    private static final List<Integer> THIRD_COLUMN;
    private static final List<Integer> RED_NUMBERS;
    private static final List<Integer> BLACK_NUMBERS;
    private int outcome;
    private RouletteOutcome redBlack;
    private RouletteOutcome oddEven;
    private RouletteOutcome half;
    private RouletteOutcome column;
    private RouletteOutcome dozen;
    private boolean isZero;

    public CompoundRouletteOutcome(int outcome) {
        validateOutcome(outcome);
        this.outcome = outcome;
        this.redBlack = redBlack(outcome);
        this.oddEven = oddEven(outcome);
        this.half = half(outcome);
        this.column = column(outcome);
        this.dozen = dozen(outcome);
        this.isZero = isZero(outcome);
    }

    static {
        FIRST_COLUMN = new ArrayList<>();
        SECOND_COLUMN =new ArrayList<>();
        THIRD_COLUMN = new ArrayList<>();
        RED_NUMBERS = new ArrayList<>();
        BLACK_NUMBERS =new ArrayList<>();

        // populating columns
        for (int i = 1, j = 0; i < 37; i += 3, j++) {
            FIRST_COLUMN.add(i);
            SECOND_COLUMN.add(i + 1);
            THIRD_COLUMN.add( i + 2);
        }

        //populating red + black
        for (int i = 1, j = 0; i < 10; i += 2, j++) {
            RED_NUMBERS.add(i);
            BLACK_NUMBERS.add(i + 1);
        }
        for (int i = 12, j = 5; i < 19; i += 2, j++) {
            RED_NUMBERS.add(i);
            BLACK_NUMBERS.add(i - 1);
        }
        for (int i = 19, j = 9; i < 28; i += 2, j++) {
            RED_NUMBERS.add(i);
            BLACK_NUMBERS.add(i + 1);
        }

        for (int i = 30, j = 14; i < 37; i += 2, j++) {
            RED_NUMBERS.add(i);
            BLACK_NUMBERS.add(i - 1);
        }
    }

    private static void validateOutcome(int outcome) {
        if (!(outcome > -1 && outcome < 37)) {
            throw new NotValidOutcomeException("Not a valid roulette outcome. RouletteOutcome can only be within the range of 0 - 36.");
        }
    }

    public void setOutcome(int outcome) {
        validateOutcome(outcome);
        this.outcome = outcome;
        this.redBlack = redBlack(outcome);
        this.oddEven = oddEven(outcome);
        this.half = half(outcome);
        this.column = column(outcome);
        this.dozen = dozen(outcome);
        this.isZero = isZero(outcome);
    }

    public int getOutcome() {
        return outcome;
    }

    public RouletteOutcome getRedBlack() {
        return redBlack;
    }

    public RouletteOutcome getOddEven() {
        return oddEven;
    }

    public RouletteOutcome getHalf() {
        return half;
    }

    public RouletteOutcome getColumn() {
        return column;
    }

    public RouletteOutcome getDozen() {
        return dozen;
    }

    public boolean isZero() {
        return isZero;
    }

    private static RouletteOutcome dozen(int currentInput) {
        if (currentInput > 0 && currentInput < 13) {
            return RouletteOutcome.FIRST;
        } else if (currentInput > 12 && currentInput < 25) {
            return RouletteOutcome.SECOND;
        } else if (currentInput > 24 && currentInput < 37) {
            return RouletteOutcome.THIRD;
        } else {
            return RouletteOutcome.ZERO;
        }
    }

    private static RouletteOutcome column(int currentInput) {
        if (FIRST_COLUMN.contains(currentInput)) {
            return RouletteOutcome.FIRST;
        } else if (SECOND_COLUMN.contains(currentInput)) {
            return RouletteOutcome.SECOND;
        } else if (THIRD_COLUMN.contains( currentInput)) {
            return RouletteOutcome.THIRD;
        } else {
            return RouletteOutcome.ZERO;
        }
    }

    private static RouletteOutcome redBlack(int currentInput) {
        if (RED_NUMBERS.contains(currentInput)) {
            return RouletteOutcome.RED;
        } else if (BLACK_NUMBERS.contains( currentInput)) {
            return RouletteOutcome.BLACK;
        } else {
            return RouletteOutcome.ZERO;
        }
    }

    private static RouletteOutcome oddEven(int currentInput) {
        if (isZero(currentInput)) {
            return RouletteOutcome.ZERO;
        } else {
            return currentInput % 2 == 0 ? RouletteOutcome.EVEN : RouletteOutcome.ODD;
        }
    }

    private static RouletteOutcome half(int currentInput) {
        if (isZero(currentInput)) {
            return RouletteOutcome.ZERO;
        } else {
            return currentInput < 19 ? RouletteOutcome.FIRST : RouletteOutcome.SECOND;
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
                        " COLUMN: " + this.column +
                        " BLACK/RED: " + this.redBlack +
                        " ODD/EVEN: " + this.oddEven +
                        " HALFS: " + this.half + " ";
    }

    public CompoundRouletteOutcomeV1 toRepresentationV1() {
        CompoundRouletteOutcomeV1 representation = new CompoundRouletteOutcomeV1();
        representation.dozen = this.dozen;
        representation.half = this.half;
        representation.isZero = this.isZero;
        representation.oddEven = this.oddEven;
        representation.redBlack = this.redBlack;
        representation.outcome = this.outcome;
        representation.column = this.column;
        return representation;
    }

}
