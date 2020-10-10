package nl.kooi.app.domain;

import nl.kooi.app.domain.advice.Advice;
import nl.kooi.app.domain.outcome.Outcome;
import nl.kooi.app.domain.rouletteoutcome.RouletteOutcome;
import nl.kooi.app.domain.rouletteoutcome.RouletteOutcomeUtilities;
import nl.kooi.app.domain.session.Session;
import nl.kooi.app.persistence.entity.AdviceEntity;
import nl.kooi.app.persistence.entity.SessionEntity;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class MapperTest {

    @Test
    public void sessionToSessionEntityMapTest() {
        var session = new Session();
        session.setChipValue(BigDecimal.TEN);
        session.setDateTime(Instant.now());
        session.setId(1234);
        session.setUserId(2345);

        var sessionEntity = Mapper.map(session);

        assertThat(sessionEntity.getId()).isEqualTo(1234);
        assertThat(sessionEntity.getUserId()).isEqualTo(2345);
        assertThat(sessionEntity.getChipValue()).isEqualTo(BigDecimal.TEN);
    }


    @Test
    public void sessionEntityToSessionMapTest() {
        var sessionEntity = new SessionEntity();
        sessionEntity.setChipValue(BigDecimal.TEN);
        sessionEntity.setDateTime(Instant.now());
        sessionEntity.setId(1234);
        sessionEntity.setUserId(2345);

        var session = Mapper.map(sessionEntity);

        assertThat(session.getId()).isEqualTo(1234);
        assertThat(session.getUserId()).isEqualTo(2345);
        assertThat(session.getChipValue()).isEqualTo(BigDecimal.TEN);
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
        assertThat(outcomeEntity.getId()).isEqualTo(1234);
        assertThat(outcomeEntity.getOutcome()).isEqualTo(10);
        assertThat(outcomeEntity.getBlack()).isEqualTo(outcome.getBlack());
        assertThat(outcomeEntity.getRed()).isEqualTo(outcome.getRed());
        assertThat(outcomeEntity.getOdd()).isEqualTo(outcome.getOdd());
        assertThat(outcomeEntity.getEven()).isEqualTo(outcome.getEven());
        assertThat(outcomeEntity.getFirstHalf()).isEqualTo(outcome.getFirstHalf());
        assertThat(outcomeEntity.getSecondHalf()).isEqualTo(outcome.getSecondHalf());
        assertThat(outcomeEntity.getFirstColumn()).isEqualTo(outcome.getFirstColumn());
        assertThat(outcomeEntity.getSecondColumn()).isEqualTo(outcome.getSecondColumn());
        assertThat(outcomeEntity.getThirdColumn()).isEqualTo(outcome.getThirdColumn());
        assertThat(outcomeEntity.getZero()).isEqualTo(outcome.getZero());
        assertThat(outcomeEntity.getFirstDozen()).isEqualTo(outcome.getFirstDozen());
        assertThat(outcomeEntity.getSecondDozen()).isEqualTo(outcome.getSecondDozen());
        assertThat(outcomeEntity.getThirdDozen()).isEqualTo(outcome.getThirdDozen());
    }

    @Test
    public void adviceToAdviceEntityMapperTest() {

        var adviceMap = new TreeMap<>(RouletteOutcomeUtilities.getCompoundRouletteOutcome(12)
                .entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey, x -> BigDecimal.valueOf(Math.random() * 100))));

        var advice = new Advice(14, adviceMap);

        var adviceEntity = Mapper.map(advice);

        assertThat(adviceEntity.getId()).isEqualTo(advice.getId());
        assertThat(adviceEntity.getRedAdvice()).isEqualTo(advice.getAdviceMap().get(RouletteOutcome.RED));
        assertThat(adviceEntity.getBlackAdvice()).isEqualTo(advice.getAdviceMap().get(RouletteOutcome.BLACK));
        assertThat(adviceEntity.getOddAdvice()).isEqualTo(advice.getAdviceMap().get(RouletteOutcome.ODD));
        assertThat(adviceEntity.getEvenAdvice()).isEqualTo(advice.getAdviceMap().get(RouletteOutcome.EVEN));
        assertThat(adviceEntity.getFirstHalfAdvice()).isEqualTo(advice.getAdviceMap().get(RouletteOutcome.FIRST_HALF));
        assertThat(adviceEntity.getSecondHalfAdvice()).isEqualTo(advice.getAdviceMap().get(RouletteOutcome.SECOND_HALF));
        assertThat(adviceEntity.getFirstColumnAdvice()).isEqualTo(advice.getAdviceMap().get(RouletteOutcome.FIRST_COLUMN));
        assertThat(adviceEntity.getSecondColumnAdvice()).isEqualTo(advice.getAdviceMap().get(RouletteOutcome.SECOND_COLUMN));
        assertThat(adviceEntity.getThirdColumnAdvice()).isEqualTo(advice.getAdviceMap().get(RouletteOutcome.THIRD_COLUMN));
        assertThat(adviceEntity.getFirstDozenAdvice()).isEqualTo(advice.getAdviceMap().get(RouletteOutcome.FIRST_DOZEN));
        assertThat(adviceEntity.getSecondDozenAdvice()).isEqualTo(advice.getAdviceMap().get(RouletteOutcome.SECOND_DOZEN));
        assertThat(adviceEntity.getThirdDozenAdvice()).isEqualTo(advice.getAdviceMap().get(RouletteOutcome.THIRD_DOZEN));
    }

    @Test
    public void adviceEntityToAdviceMapperTest() {

        var adviceEntity = new AdviceEntity();

        adviceEntity.setId(12);
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

        assertThat(advice.getId()).isEqualTo(adviceEntity.getId());
        assertThat(advice.getAdviceMap().get(RouletteOutcome.RED)).isEqualTo(adviceEntity.getRedAdvice());
        assertThat(advice.getAdviceMap().get(RouletteOutcome.BLACK)).isEqualTo(adviceEntity.getBlackAdvice());
        assertThat(advice.getAdviceMap().get(RouletteOutcome.ODD)).isEqualTo(adviceEntity.getOddAdvice());
        assertThat(advice.getAdviceMap().get(RouletteOutcome.EVEN)).isEqualTo(adviceEntity.getEvenAdvice());
        assertThat(advice.getAdviceMap().get(RouletteOutcome.FIRST_HALF)).isEqualTo(adviceEntity.getFirstHalfAdvice());
        assertThat(advice.getAdviceMap().get(RouletteOutcome.SECOND_HALF)).isEqualTo(adviceEntity.getSecondHalfAdvice());
        assertThat(advice.getAdviceMap().get(RouletteOutcome.FIRST_COLUMN)).isEqualTo(adviceEntity.getFirstColumnAdvice());
        assertThat(advice.getAdviceMap().get(RouletteOutcome.SECOND_COLUMN)).isEqualTo(adviceEntity.getSecondColumnAdvice());
        assertThat(advice.getAdviceMap().get(RouletteOutcome.THIRD_COLUMN)).isEqualTo(adviceEntity.getThirdColumnAdvice());
        assertThat(advice.getAdviceMap().get(RouletteOutcome.FIRST_DOZEN)).isEqualTo(adviceEntity.getFirstDozenAdvice());
        assertThat(advice.getAdviceMap().get(RouletteOutcome.SECOND_DOZEN)).isEqualTo(adviceEntity.getSecondDozenAdvice());
        assertThat(advice.getAdviceMap().get(RouletteOutcome.THIRD_DOZEN)).isEqualTo(adviceEntity.getThirdDozenAdvice());
    }


}
