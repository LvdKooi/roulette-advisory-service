package nl.kooi.app.api.dto;

import lombok.var;
import nl.kooi.app.domain.outcome.Outcome;
import org.junit.Test;

import java.math.BigDecimal;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class MapperTest {

    @Test
    public void outcomeToOutcomeDtoMapTest(){

        var outcome = new Outcome();
        outcome.setBlack(false);
        outcome.setRed(true);
        outcome.setOdd(true);
        outcome.setEven(false);
        outcome.setFirstHalf(false);
        outcome.setSecondHalf(true);
        outcome.setFirstColumn(true);
        outcome.setSecondColumn(false);
        outcome.setThirdColumn(false);
        outcome.setZero(false);
        outcome.setFirstDozen(true);
        outcome.setSecondDozen(false);
        outcome.setThirdDozen(false);
        outcome.setId(1234);
        outcome.setSessionId(2345);
        outcome.setTotalProfit(BigDecimal.TEN);
        outcome.setOutcome(10);

        var outcomeDto = Mapper.map(outcome);

        assertThat("Id of OutcomeDto doesn't match the one of Outcome.", 1234, equalTo(outcomeDto.id));
        assertThat("Outcome of OutcomeDto doesn't match the one of Outcome.", 10, equalTo(outcomeDto.outcome));
    }

}
