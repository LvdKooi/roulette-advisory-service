package nl.kooi.app.domain;

import lombok.var;
import nl.kooi.app.domain.outcome.Outcome;
import nl.kooi.app.domain.session.Session;
import nl.kooi.infrastructure.entity.SessionEntity;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class MapperTest {

    @Test
    public void sessionToSessionEntityMapTest() {
        var session = new Session();
        session.setChipValue(BigDecimal.TEN);
        session.setDateTime(LocalDateTime.now());
        session.setId(1234);
        session.setUserId(2345);

        var sessionEntity = Mapper.map(session);

        assertThat("Id of SessionEntity doesn't match the one of Session.", 1234, equalTo(sessionEntity.getId()));
        assertThat("UserId of SessionEntity doesn't match the one of Session.", 2345, equalTo(sessionEntity.getUserId()));
        assertThat("ChipValue of SessionEntity doesn't match the one of Session.", BigDecimal.TEN, equalTo(sessionEntity.getChipValue()));
    }


    @Test
    public void sessionEntityToSessionMapTest() {
        var sessionEntity = new SessionEntity();
        sessionEntity.setChipValue(BigDecimal.TEN);
        sessionEntity.setDateTime(LocalDateTime.now());
        sessionEntity.setId(1234);
        sessionEntity.setUserId(2345);

        var session = Mapper.map(sessionEntity);

        assertThat("Id of Session doesn't match the one of SessionEntity.", 1234, equalTo(session.getId()));
        assertThat("UserId of Session doesn't match the one of SessionEntity.", 2345, equalTo(session.getUserId()));
        assertThat("ChipValue of Session doesn't match the one of SessionEntity.", BigDecimal.TEN, equalTo(session.getChipValue()));
    }

    @Test
    public void outcomeToOutcomeEntityMapTest() {
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

        var outcomeEntity = Mapper.map(outcome);
        assertThat("Id of OutcomeEntity doesn't match the one of Outcome.", 1234, equalTo(outcomeEntity.getId()));
        assertThat("Outcome of OutcomeEntity doesn't match the one of Outcome.", 10, equalTo(outcomeEntity.getOutcome()));
        assertThat("A RouletteOutcome of OutcomeEntity doesn't match the one of Outcome.", outcome.getBlack(), equalTo(outcomeEntity.getBlack()));
        assertThat("A RouletteOutcome of OutcomeEntity doesn't match the one of Outcome.", outcome.getRed(), equalTo(outcomeEntity.getRed()));
        assertThat("A RouletteOutcome of OutcomeEntity doesn't match the one of Outcome.", outcome.getOdd(), equalTo(outcomeEntity.getOdd()));
        assertThat("A RouletteOutcome of OutcomeEntity doesn't match the one of Outcome.", outcome.getEven(), equalTo(outcomeEntity.getEven()));
        assertThat("A RouletteOutcome of OutcomeEntity doesn't match the one of Outcome.", outcome.getFirstHalf(), equalTo(outcomeEntity.getFirstHalf()));
        assertThat("A RouletteOutcome of OutcomeEntity doesn't match the one of Outcome.", outcome.getSecondHalf(), equalTo(outcomeEntity.getSecondHalf()));
        assertThat("A RouletteOutcome of OutcomeEntity doesn't match the one of Outcome.", outcome.getFirstColumn(), equalTo(outcomeEntity.getFirstColumn()));
        assertThat("A RouletteOutcome of OutcomeEntity doesn't match the one of Outcome.", outcome.getSecondColumn(), equalTo(outcomeEntity.getSecondColumn()));
        assertThat("A RouletteOutcome of OutcomeEntity doesn't match the one of Outcome.", outcome.getThirdColumn(), equalTo(outcomeEntity.getThirdColumn()));
        assertThat("A RouletteOutcome of OutcomeEntity doesn't match the one of Outcome.", outcome.getZero(), equalTo(outcomeEntity.getZero()));
        assertThat("A RouletteOutcome of OutcomeEntity doesn't match the one of Outcome.", outcome.getFirstDozen(), equalTo(outcomeEntity.getFirstDozen()));
        assertThat("A RouletteOutcome of OutcomeEntity doesn't match the one of Outcome.", outcome.getSecondDozen(), equalTo(outcomeEntity.getSecondDozen()));
        assertThat("A RouletteOutcome of OutcomeEntity doesn't match the one of Outcome.", outcome.getThirdDozen(), equalTo(outcomeEntity.getThirdDozen()));
    }

}
