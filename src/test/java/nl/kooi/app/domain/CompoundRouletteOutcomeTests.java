package nl.kooi.app.domain;

import nl.kooi.app.domain.rouletteoutcome.CompoundRouletteOutcome;
import nl.kooi.app.exceptions.NotValidOutcomeException;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class CompoundRouletteOutcomeTests {

    @Test
    public void invalidOutcomes() {
        int[] outcomeArray = {-1,-2,37,38};
        for (int outcome : outcomeArray) {
            try {
                CompoundRouletteOutcome domainObject = new CompoundRouletteOutcome(outcome);

            } catch (NotValidOutcomeException e) {
                assertThat("Invalid outcome gives a clear exception", e.getMessage(), is("Not a valid roulette outcome. RouletteOutcome can only be within the range of 0 - 36."));
            }
        }
    }

//    TODO: make working
    @Test
    public void validOutcomes() {
        int[] outcomeArray = {1, 2, 3, 13, 14, 15, 34, 35, 36};
        for (int outcome : outcomeArray) {
            CompoundRouletteOutcome domainObject = new CompoundRouletteOutcome(outcome);
            System.out.println(domainObject);
        }
    }
}
