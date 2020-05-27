package nl.kooi.app.domain.rouletteoutcome;

import lombok.var;
import nl.kooi.app.exception.NotValidOutcomeException;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import static nl.kooi.app.domain.rouletteoutcome.RouletteOutcome.*;
import static org.junit.Assert.*;

public class RouletteOutcomeUtilitiesTest {


    private static final List<Integer> RED_NUMBERS = Arrays.asList(1, 3, 5, 7, 9, 12, 14, 16, 18, 19, 21, 23, 25, 27, 30, 32, 34, 36);
    private static final List<Integer> BLACK_NUMBERS = Arrays.asList(2, 4, 6, 8, 10, 11, 13, 15, 17, 20, 22, 24, 26, 28, 29, 31, 33, 35);
    private static final List<Integer> FIRST_COLUMN_NUMBERS = Arrays.asList(1, 4, 7, 10, 13, 16, 19, 22, 25, 28, 31, 34);
    private static final List<Integer> SECOND_COLUMN_NUMBERS = Arrays.asList(2, 5, 8, 11, 14, 17, 20, 23, 26, 29, 32, 35);
    private static final List<Integer> THIRD_COLUMN_NUMBERS = Arrays.asList(3, 6, 9, 12, 15, 18, 21, 24, 27, 30, 33, 36);

    @Test
    public void redTest() {
        assertTrue("One of the red numbers doesn't return a RED outcome.", RED_NUMBERS.stream().allMatch(number -> RouletteOutcomeUtilities.redBlack(number).equals(RED)));
        assertTrue("One of the red numbers doesn't return a true in the CompoundRouletteOutcome.", RED_NUMBERS.stream().allMatch(number -> RouletteOutcomeUtilities.getCompoundRouletteOutcome(number).get(RED)));

    }

    @Test
    public void blackTest() {
        assertTrue("One of the black numbers doesn't return a BLACK outcome.", BLACK_NUMBERS.stream().allMatch(number -> RouletteOutcomeUtilities.redBlack(number).equals(BLACK)));
        assertTrue("One of the black numbers doesn't return a true in the CompoundRouletteOutcome.", BLACK_NUMBERS.stream().allMatch(number -> RouletteOutcomeUtilities.getCompoundRouletteOutcome(number).get(BLACK)));
    }


    @Test
    public void firstHalfTest() {
        assertTrue("One of the first half numbers doesn't return a FIRST_HALF outcome.", IntStream.rangeClosed(1, 18).allMatch(number -> RouletteOutcomeUtilities.half(number).equals(FIRST_HALF)));
        assertTrue("One of the first half numbers doesn't return a true in the CompoundRouletteOutcome.", IntStream.rangeClosed(1, 18).allMatch(number -> RouletteOutcomeUtilities.getCompoundRouletteOutcome(number).get(FIRST_HALF)));
    }

    @Test
    public void secondHalfTest() {
        assertTrue("One of the second half numbers doesn't return a SECOND_HALF outcome.", IntStream.rangeClosed(19, 36).allMatch(number -> RouletteOutcomeUtilities.half(number).equals(SECOND_HALF)));
        assertTrue("One of the second half numbers doesn't return a true in the CompoundRouletteOutcome.", IntStream.rangeClosed(19, 36).allMatch(number -> RouletteOutcomeUtilities.getCompoundRouletteOutcome(number).get(SECOND_HALF)));
    }


    @Test
    public void oddTest() {
        assertTrue("One of the odd numbers doesn't return a ODD outcome.", IntStream.rangeClosed(1, 36).filter(number -> number % 2 != 0).allMatch(number -> RouletteOutcomeUtilities.oddEven(number).equals(ODD)));
        assertTrue("One of the odd numbers doesn't return a true in the CompoundRouletteOutcome.", IntStream.rangeClosed(1, 36).filter(number -> number % 2 != 0).allMatch(number -> RouletteOutcomeUtilities.getCompoundRouletteOutcome(number).get(ODD)));
    }

    @Test
    public void evenTest() {
        assertTrue("One of the even numbers doesn't return a EVEN outcome.", IntStream.rangeClosed(1, 36).filter(number -> number % 2 == 0).allMatch(number -> RouletteOutcomeUtilities.oddEven(number).equals(EVEN)));
        assertTrue("One of the even numbers doesn't return a true in the CompoundRouletteOutcome.", IntStream.rangeClosed(1, 36).filter(number -> number % 2 == 0).allMatch(number -> RouletteOutcomeUtilities.getCompoundRouletteOutcome(number).get(EVEN)));
    }

    @Test
    public void firstDozenTest() {
        assertTrue("One of the first dozen numbers doesn't return a FIRST_DOZEN outcome.", IntStream.rangeClosed(1, 12).allMatch(number -> RouletteOutcomeUtilities.dozen(number).equals(FIRST_DOZEN)));
        assertTrue("One of the first dozen numbers doesn't return a true in the CompoundRouletteOutcome.", IntStream.rangeClosed(1, 12).allMatch(number -> RouletteOutcomeUtilities.getCompoundRouletteOutcome(number).get(FIRST_DOZEN)));
    }

    @Test
    public void secondDozenTest() {
        assertTrue("One of the second dozen numbers doesn't return a SECOND_DOZEN outcome.", IntStream.rangeClosed(13, 24).allMatch(number -> RouletteOutcomeUtilities.dozen(number).equals(SECOND_DOZEN)));
        assertTrue("One of the second dozen numbers doesn't return a true in the CompoundRouletteOutcome.", IntStream.rangeClosed(13, 24).allMatch(number -> RouletteOutcomeUtilities.getCompoundRouletteOutcome(number).get(SECOND_DOZEN)));
    }

    @Test
    public void thirdDozenTest() {
        assertTrue("One of the third dozen numbers doesn't return a THIRD_DOZEN outcome.", IntStream.rangeClosed(25, 36).allMatch(number -> RouletteOutcomeUtilities.dozen(number).equals(THIRD_DOZEN)));
        assertTrue("One of the third dozen numbers doesn't return a true in the CompoundRouletteOutcome.", IntStream.rangeClosed(25, 36).allMatch(number -> RouletteOutcomeUtilities.getCompoundRouletteOutcome(number).get(THIRD_DOZEN)));
    }

    @Test
    public void firstColumnTest() {
        assertTrue("One of the first column numbers doesn't return a FIRST_COLUMN outcome.", FIRST_COLUMN_NUMBERS.stream().allMatch(number -> RouletteOutcomeUtilities.column(number).equals(FIRST_COLUMN)));
        assertTrue("One of the first column numbers doesn't return a true in the CompoundRouletteOutcome.", FIRST_COLUMN_NUMBERS.stream().allMatch(number -> RouletteOutcomeUtilities.getCompoundRouletteOutcome(number).get(FIRST_COLUMN)));
    }

    @Test
    public void secondColumnTest() {
        assertTrue("One of the second column numbers doesn't return a SECOND_COLUMN outcome.", SECOND_COLUMN_NUMBERS.stream().allMatch(number -> RouletteOutcomeUtilities.column(number).equals(SECOND_COLUMN)));
        assertTrue("One of the second column numbers doesn't return a true in the CompoundRouletteOutcome.", SECOND_COLUMN_NUMBERS.stream().allMatch(number -> RouletteOutcomeUtilities.getCompoundRouletteOutcome(number).get(SECOND_COLUMN)));
    }

    @Test
    public void thirdColumnTest() {
        assertTrue("One of the third column numbers doesn't return a THIRD_COLUMN outcome.", THIRD_COLUMN_NUMBERS.stream().allMatch(number -> RouletteOutcomeUtilities.column(number).equals(THIRD_COLUMN)));
        assertTrue("One of the third column numbers doesn't return a true in the CompoundRouletteOutcome.", THIRD_COLUMN_NUMBERS.stream().allMatch(number -> RouletteOutcomeUtilities.getCompoundRouletteOutcome(number).get(THIRD_COLUMN)));
    }

    @Test
    public void zeroTest() {
        assertTrue("Zero doesn't return a ZERO outcome.", RouletteOutcomeUtilities.isZero(0));
        assertEquals("Zero returns a RED/BLACK outcome", RouletteOutcomeUtilities.redBlack(0), ZERO);
        assertEquals("Zero returns a ODD/EVEN outcome", RouletteOutcomeUtilities.oddEven(0), ZERO);
        assertEquals("Zero returns a FIRST_HALF/SECOND_HALF outcome", RouletteOutcomeUtilities.half(0), ZERO);
        assertEquals("Zero returns a DOZEN outcome", RouletteOutcomeUtilities.dozen(0), ZERO);
        assertEquals("Zero returns a COLUMN outcome", RouletteOutcomeUtilities.column(0), ZERO);
        assertTrue("Zero returns a true in the CompoundRouletteOutcome.", RouletteOutcomeUtilities.isZero(0));
    }

    @Test(expected = NotValidOutcomeException.class)
    public void validateOutcomeBelow0Test() {
        RouletteOutcomeUtilities.validateOutcome(-1);

    }

    @Test(expected = NotValidOutcomeException.class)
    public void validateOutcomeOver36Test() {
        RouletteOutcomeUtilities.validateOutcome(37);

    }

    @Test
    public void validateOutcomeValidOutcomesTest() {
        String errorMessage = null;
        try {
            IntStream.rangeClosed(0, 36).forEach(RouletteOutcomeUtilities::validateOutcome);
        } catch (NotValidOutcomeException e) {
            errorMessage = e.getMessage();
        }
        assertNull(errorMessage);

    }

}
