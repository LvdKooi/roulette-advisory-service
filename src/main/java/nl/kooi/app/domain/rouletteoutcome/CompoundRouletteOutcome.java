package nl.kooi.app.domain.rouletteoutcome;

import nl.kooi.app.api.dto.CompoundRouletteOutcomeDto;
import nl.kooi.app.exceptions.NotValidOutcomeException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static nl.kooi.app.domain.rouletteoutcome.RouletteOutcome.*;

/**
 * @author Laurens van der Kooi
 */

public class CompoundRouletteOutcome {

    private static final List<Integer> FIRST_COLUMN_LIST;
    private static final List<Integer> SECOND_COLUMN_LIST;
    private static final List<Integer> THIRD_COLUMN_LIST;
    private static final List<Integer> RED_NUMBERS_LIST;
    private static final List<Integer> BLACK_NUMBERS_LIST;
    private final Map<RouletteOutcome, Boolean> outcomeBooleanMap;
    private int outcome;

    public CompoundRouletteOutcome(int outcome) {
        validateOutcome(outcome);
        outcomeBooleanMap = new HashMap<>();
        outcomeBooleanMap.put(BLACK, BLACK.equals(redBlack(outcome)));
        outcomeBooleanMap.put(RED, RED.equals(redBlack(outcome)));
        outcomeBooleanMap.put(ODD, ODD.equals(oddEven(outcome)));
        outcomeBooleanMap.put(EVEN, EVEN.equals(oddEven(outcome)));
        outcomeBooleanMap.put(FIRST_HALF, FIRST_HALF.equals(half(outcome)));
        outcomeBooleanMap.put(SECOND_HALF, SECOND_HALF.equals(half(outcome)));
        outcomeBooleanMap.put(FIRST_COLUMN, FIRST_COLUMN.equals(column(outcome)));
        outcomeBooleanMap.put(SECOND_COLUMN, SECOND_COLUMN.equals(column(outcome)));
        outcomeBooleanMap.put(THIRD_COLUMN, THIRD_COLUMN.equals(column(outcome)));
        outcomeBooleanMap.put(FIRST_DOZEN, FIRST_DOZEN.equals(dozen(outcome)));
        outcomeBooleanMap.put(SECOND_DOZEN, SECOND_DOZEN.equals(dozen(outcome)));
        outcomeBooleanMap.put(THIRD_DOZEN, THIRD_DOZEN.equals(dozen(outcome)));
        outcomeBooleanMap.put(ZERO, isZero(outcome));
        this.outcome = outcome;
    }

    static {
        FIRST_COLUMN_LIST = new ArrayList<>();
        SECOND_COLUMN_LIST =new ArrayList<>();
        THIRD_COLUMN_LIST = new ArrayList<>();
        RED_NUMBERS_LIST = new ArrayList<>();
        BLACK_NUMBERS_LIST =new ArrayList<>();

        // populating columns
        for (int i = 1, j = 0; i < 37; i += 3, j++) {
            FIRST_COLUMN_LIST.add(i);
            SECOND_COLUMN_LIST.add(i + 1);
            THIRD_COLUMN_LIST.add( i + 2);
        }

        //populating red + black
        for (int i = 1, j = 0; i < 10; i += 2, j++) {
            RED_NUMBERS_LIST.add(i);
            BLACK_NUMBERS_LIST.add(i + 1);
        }
        for (int i = 12, j = 5; i < 19; i += 2, j++) {
            RED_NUMBERS_LIST.add(i);
            BLACK_NUMBERS_LIST.add(i - 1);
        }
        for (int i = 19, j = 9; i < 28; i += 2, j++) {
            RED_NUMBERS_LIST.add(i);
            BLACK_NUMBERS_LIST.add(i + 1);
        }

        for (int i = 30, j = 14; i < 37; i += 2, j++) {
            RED_NUMBERS_LIST.add(i);
            BLACK_NUMBERS_LIST.add(i - 1);
        }
    }

    public static void validateOutcome(int outcome) {
        if (!(outcome > -1 && outcome < 37)) {
            throw new NotValidOutcomeException("Not a valid roulette outcome. RouletteOutcome can only be within the range of 0 - 36.");
        }
    }

    private static RouletteOutcome dozen(int currentInput) {
        if (currentInput > 0 && currentInput < 13) {
            return FIRST_DOZEN;
        } else if (currentInput > 12 && currentInput < 25) {
            return SECOND_DOZEN;
        } else if (currentInput > 24 && currentInput < 37) {
            return THIRD_DOZEN;
        } else {
            return RouletteOutcome.ZERO;
        }
    }

    private static RouletteOutcome column(int currentInput) {
        if (FIRST_COLUMN_LIST.contains(currentInput)) {
            return RouletteOutcome.FIRST_COLUMN;
        } else if (SECOND_COLUMN_LIST.contains(currentInput)) {
            return RouletteOutcome.SECOND_COLUMN;
        } else if (THIRD_COLUMN_LIST.contains( currentInput)) {
            return RouletteOutcome.THIRD_COLUMN;
        } else {
            return RouletteOutcome.ZERO;
        }
    }

    private static RouletteOutcome redBlack(int currentInput) {
        if (RED_NUMBERS_LIST.contains(currentInput)) {
            return RED;
        } else if (BLACK_NUMBERS_LIST.contains( currentInput)) {
            return BLACK;
        } else {
            return RouletteOutcome.ZERO;
        }
    }

    private static RouletteOutcome oddEven(int currentInput) {
        if (isZero(currentInput)) {
            return RouletteOutcome.ZERO;
        } else {
            return currentInput % 2 == 0 ? RouletteOutcome.EVEN : ODD;
        }
    }

    private static RouletteOutcome half(int currentInput) {
        if (isZero(currentInput)) {
            return RouletteOutcome.ZERO;
        } else {
            return currentInput < 19 ? FIRST_HALF : SECOND_HALF;
        }
    }

    private static boolean isZero(int currentInput) {
        return currentInput == 0;
    }

    public int getOutcome() {
        return outcome;
    }

    public Map<RouletteOutcome, Boolean> getOutcomeBooleanMap(){
        return this.outcomeBooleanMap;
    }

    @Override
    public String toString() {
        return
                "OUTCOME: " + this.outcome +
                outcomeBooleanMap;
    }

    public CompoundRouletteOutcomeDto toRepresentationV1() {
        CompoundRouletteOutcomeDto representation = new CompoundRouletteOutcomeDto();
        representation.outcome = this.outcome;
        representation.outcomeList = outcomeBooleanMap
                .entrySet()
                .stream()
                .filter(Map.Entry::getValue)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
            return representation;
    }

}
