package nl.kooi.app.domain;

import lombok.var;
import nl.kooi.app.domain.advises.Advice;
import nl.kooi.app.domain.outcome.Outcome;
import nl.kooi.app.domain.rouletteoutcome.RouletteOutcome;
import nl.kooi.app.domain.rouletteoutcome.RouletteOutcomeUtilities;
import nl.kooi.app.domain.session.Session;
import nl.kooi.infrastructure.entity.AdviceEntity;
import nl.kooi.infrastructure.entity.SessionEntity;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

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

    @Test
    public void adviceToAdviceEntityMapperTest() {

        var adviceMap = new TreeMap<>(RouletteOutcomeUtilities.getCompoundRouletteOutcome(12)
                .entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey, x -> BigDecimal.valueOf(Math.random() * 100))));

        var advice = new Advice(adviceMap);

        var adviceEntity = Mapper.map(advice);

        assertThat("An advice of AdviceEntity doesn't match the one of Advice.", advice.getAdviceMap().get(RouletteOutcome.RED), equalTo(adviceEntity.getRedAdvice()));
        assertThat("An advice of AdviceEntity doesn't match the one of Advice.", advice.getAdviceMap().get(RouletteOutcome.BLACK), equalTo(adviceEntity.getBlackAdvice()));
        assertThat("An advice of AdviceEntity doesn't match the one of Advice.", advice.getAdviceMap().get(RouletteOutcome.ODD), equalTo(adviceEntity.getOddAdvice()));
        assertThat("An advice of AdviceEntity doesn't match the one of Advice.", advice.getAdviceMap().get(RouletteOutcome.EVEN), equalTo(adviceEntity.getEvenAdvice()));
        assertThat("An advice of AdviceEntity doesn't match the one of Advice.", advice.getAdviceMap().get(RouletteOutcome.FIRST_HALF), equalTo(adviceEntity.getFirstHalfAdvice()));
        assertThat("An advice of AdviceEntity doesn't match the one of Advice.", advice.getAdviceMap().get(RouletteOutcome.SECOND_HALF), equalTo(adviceEntity.getSecondHalfAdvice()));
        assertThat("An advice of AdviceEntity doesn't match the one of Advice.", advice.getAdviceMap().get(RouletteOutcome.FIRST_COLUMN), equalTo(adviceEntity.getFirstColumnAdvice()));
        assertThat("An advice of AdviceEntity doesn't match the one of Advice.", advice.getAdviceMap().get(RouletteOutcome.SECOND_COLUMN), equalTo(adviceEntity.getSecondColumnAdvice()));
        assertThat("An advice of AdviceEntity doesn't match the one of Advice.", advice.getAdviceMap().get(RouletteOutcome.THIRD_COLUMN), equalTo(adviceEntity.getThirdColumnAdvice()));
        assertThat("An advice of AdviceEntity doesn't match the one of Advice.", advice.getAdviceMap().get(RouletteOutcome.FIRST_DOZEN), equalTo(adviceEntity.getFirstDozenAdvice()));
        assertThat("An advice of AdviceEntity doesn't match the one of Advice.", advice.getAdviceMap().get(RouletteOutcome.SECOND_DOZEN), equalTo(adviceEntity.getSecondDozenAdvice()));
        assertThat("An advice of AdviceEntity doesn't match the one of Advice.", advice.getAdviceMap().get(RouletteOutcome.THIRD_DOZEN), equalTo(adviceEntity.getThirdDozenAdvice()));
    }

    @Test
    public void adviceEntityToAdviceMapperTest() {

        var adviceEntity = new AdviceEntity();

        adviceEntity.setRedAdvice(BigDecimal.valueOf(10L));
        adviceEntity.setBlackAdvice(BigDecimal.valueOf(11L));
        adviceEntity.setOddAdvice(BigDecimal.valueOf(12L));
        adviceEntity.setEvenAdvice(BigDecimal.valueOf(13L));
        adviceEntity.setFirstHalfAdvice(BigDecimal.valueOf(14L));
        adviceEntity.setSecondHalfAdvice(BigDecimal.valueOf(15L));
        adviceEntity.setFirstColumnAdvice(BigDecimal.valueOf(16L));
        adviceEntity.setSecondColumnAdvice(BigDecimal.valueOf(17L));
        adviceEntity.setThirdColumnAdvice(BigDecimal.valueOf(18L));
        adviceEntity.setFirstDozenAdvice(BigDecimal.valueOf(19L));
        adviceEntity.setSecondDozenAdvice(BigDecimal.valueOf(1L));
        adviceEntity.setThirdDozenAdvice(BigDecimal.valueOf(2L));

        var advice = Mapper.map(adviceEntity);

        assertThat("An advice of Advice doesn't match the one of AdviceEntity.", adviceEntity.getRedAdvice(), equalTo(advice.getAdviceMap().get(RouletteOutcome.RED)));
        assertThat("An advice of Advice doesn't match the one of AdviceEntity.", adviceEntity.getBlackAdvice(), equalTo(advice.getAdviceMap().get(RouletteOutcome.BLACK)));
        assertThat("An advice of Advice doesn't match the one of AdviceEntity.", adviceEntity.getOddAdvice(), equalTo(advice.getAdviceMap().get(RouletteOutcome.ODD)));
        assertThat("An advice of Advice doesn't match the one of AdviceEntity.", adviceEntity.getEvenAdvice(), equalTo(advice.getAdviceMap().get(RouletteOutcome.EVEN)));
        assertThat("An advice of Advice doesn't match the one of AdviceEntity.", adviceEntity.getFirstHalfAdvice(), equalTo(advice.getAdviceMap().get(RouletteOutcome.FIRST_HALF)));
        assertThat("An advice of Advice doesn't match the one of AdviceEntity.", adviceEntity.getSecondHalfAdvice(), equalTo(advice.getAdviceMap().get(RouletteOutcome.SECOND_HALF)));
        assertThat("An advice of Advice doesn't match the one of AdviceEntity.", adviceEntity.getFirstColumnAdvice(), equalTo(advice.getAdviceMap().get(RouletteOutcome.FIRST_COLUMN)));
        assertThat("An advice of Advice doesn't match the one of AdviceEntity.", adviceEntity.getSecondColumnAdvice(), equalTo(advice.getAdviceMap().get(RouletteOutcome.SECOND_COLUMN)));
        assertThat("An advice of Advice doesn't match the one of AdviceEntity.", adviceEntity.getThirdColumnAdvice(), equalTo(advice.getAdviceMap().get(RouletteOutcome.THIRD_COLUMN)));
        assertThat("An advice of Advice doesn't match the one of AdviceEntity.", adviceEntity.getFirstDozenAdvice(), equalTo(advice.getAdviceMap().get(RouletteOutcome.FIRST_DOZEN)));
        assertThat("An advice of Advice doesn't match the one of AdviceEntity.", adviceEntity.getSecondDozenAdvice(), equalTo(advice.getAdviceMap().get(RouletteOutcome.SECOND_DOZEN)));
        assertThat("An advice of Advice doesn't match the one of AdviceEntity.", adviceEntity.getThirdDozenAdvice(), equalTo(advice.getAdviceMap().get(RouletteOutcome.THIRD_DOZEN)));
    }


}
