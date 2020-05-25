package nl.kooi.app.domain.outcome;

import lombok.var;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class OutcomeTest {


    @Test
    public void outcomeTestOne() {
        var outcome = new Outcome(1,1, BigDecimal.TEN);

        assertThat("SessionId of outcome doesn't match expectation", outcome.getSessionId(), equalTo(1));
        assertThat("TotalProfit of outcome doesn't match expectation", outcome.getTotalProfit(), equalTo(BigDecimal.TEN.setScale(2, RoundingMode.HALF_UP)));

        assertThat("Red outcome doesn't match expectation", outcome.getRed(), equalTo(true));
        assertThat("Black outcome doesn't match expectation", outcome.getBlack(), equalTo(false));
        assertThat("Odd outcome doesn't match expectation", outcome.getOdd(), equalTo(true));
        assertThat("Even outcome doesn't match expectation", outcome.getEven(), equalTo(false));
        assertThat("FirstHalf outcome doesn't match expectation", outcome.getFirstHalf(), equalTo(true));
        assertThat("SecondHalf outcome doesn't match expectation", outcome.getSecondHalf(), equalTo(false));
        assertThat("FirstColumn outcome doesn't match expectation", outcome.getFirstColumn(), equalTo(true));
        assertThat("SecondColumn outcome doesn't match expectation", outcome.getSecondColumn(), equalTo(false));
        assertThat("ThirdColumn outcome doesn't match expectation", outcome.getThirdColumn(), equalTo(false));
        assertThat("FirstDozen outcome doesn't match expectation", outcome.getFirstDozen(), equalTo(true));
        assertThat("SecondDozen outcome doesn't match expectation", outcome.getSecondDozen(), equalTo(false));
        assertThat("ThirdDozen outcome doesn't match expectation", outcome.getThirdDozen(), equalTo(false));
        assertThat("Zero outcome doesn't match expectation", outcome.getZero(), equalTo(false));
    }

    @Test
    public void outcomeTestSeventeen() {
        var outcome = new Outcome(1,17, BigDecimal.TEN);

        assertThat("Red outcome doesn't match expectation", outcome.getRed(), equalTo(false));
        assertThat("Black outcome doesn't match expectation", outcome.getBlack(), equalTo(true));
        assertThat("Odd outcome doesn't match expectation", outcome.getOdd(), equalTo(true));
        assertThat("Even outcome doesn't match expectation", outcome.getEven(), equalTo(false));
        assertThat("FirstHalf outcome doesn't match expectation", outcome.getFirstHalf(), equalTo(true));
        assertThat("SecondHalf outcome doesn't match expectation", outcome.getSecondHalf(), equalTo(false));
        assertThat("FirstColumn outcome doesn't match expectation", outcome.getFirstColumn(), equalTo(false));
        assertThat("SecondColumn outcome doesn't match expectation", outcome.getSecondColumn(), equalTo(true));
        assertThat("ThirdColumn outcome doesn't match expectation", outcome.getThirdColumn(), equalTo(false));
        assertThat("FirstDozen outcome doesn't match expectation", outcome.getFirstDozen(), equalTo(false));
        assertThat("SecondDozen outcome doesn't match expectation", outcome.getSecondDozen(), equalTo(true));
        assertThat("ThirdDozen outcome doesn't match expectation", outcome.getThirdDozen(), equalTo(false));
        assertThat("Zero outcome doesn't match expectation", outcome.getZero(), equalTo(false));
    }

    @Test
    public void outcomeTestThirty() {
        var outcome = new Outcome(1,30, BigDecimal.TEN);

        assertThat("Red outcome doesn't match expectation", outcome.getRed(), equalTo(true));
        assertThat("Black outcome doesn't match expectation", outcome.getBlack(), equalTo(false));
        assertThat("Odd outcome doesn't match expectation", outcome.getOdd(), equalTo(false));
        assertThat("Even outcome doesn't match expectation", outcome.getEven(), equalTo(true));
        assertThat("FirstHalf outcome doesn't match expectation", outcome.getFirstHalf(), equalTo(false));
        assertThat("SecondHalf outcome doesn't match expectation", outcome.getSecondHalf(), equalTo(true));
        assertThat("FirstColumn outcome doesn't match expectation", outcome.getFirstColumn(), equalTo(false));
        assertThat("SecondColumn outcome doesn't match expectation", outcome.getSecondColumn(), equalTo(false));
        assertThat("ThirdColumn outcome doesn't match expectation", outcome.getThirdColumn(), equalTo(true));
        assertThat("FirstDozen outcome doesn't match expectation", outcome.getFirstDozen(), equalTo(false));
        assertThat("SecondDozen outcome doesn't match expectation", outcome.getSecondDozen(), equalTo(false));
        assertThat("ThirdDozen outcome doesn't match expectation", outcome.getThirdDozen(), equalTo(true));
        assertThat("Zero outcome doesn't match expectation", outcome.getZero(), equalTo(false));
    }

    @Test
    public void outcomeTestZero() {
        var outcome = new Outcome(1,0, BigDecimal.TEN);

        assertThat("Red outcome doesn't match expectation", outcome.getRed(), equalTo(false));
        assertThat("Black outcome doesn't match expectation", outcome.getBlack(), equalTo(false));
        assertThat("Odd outcome doesn't match expectation", outcome.getOdd(), equalTo(false));
        assertThat("Even outcome doesn't match expectation", outcome.getEven(), equalTo(false));
        assertThat("FirstHalf outcome doesn't match expectation", outcome.getFirstHalf(), equalTo(false));
        assertThat("SecondHalf outcome doesn't match expectation", outcome.getSecondHalf(), equalTo(false));
        assertThat("FirstColumn outcome doesn't match expectation", outcome.getFirstColumn(), equalTo(false));
        assertThat("SecondColumn outcome doesn't match expectation", outcome.getSecondColumn(), equalTo(false));
        assertThat("ThirdColumn outcome doesn't match expectation", outcome.getThirdColumn(), equalTo(false));
        assertThat("FirstDozen outcome doesn't match expectation", outcome.getFirstDozen(), equalTo(false));
        assertThat("SecondDozen outcome doesn't match expectation", outcome.getSecondDozen(), equalTo(false));
        assertThat("ThirdDozen outcome doesn't match expectation", outcome.getThirdDozen(), equalTo(false));
        assertThat("Zero outcome doesn't match expectation", outcome.getZero(), equalTo(true));
    }

}
