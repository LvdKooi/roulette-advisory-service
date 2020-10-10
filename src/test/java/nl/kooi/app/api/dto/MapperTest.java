package nl.kooi.app.api.dto;

import nl.kooi.app.domain.advice.Advice;
import nl.kooi.app.domain.metric.SessionMetrics;
import nl.kooi.app.domain.outcome.Outcome;
import nl.kooi.app.domain.rouletteoutcome.RouletteOutcomeUtilities;
import nl.kooi.app.domain.session.Session;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class MapperTest {

    @Test
    public void outcomeToOutcomeDtoMapTest() {

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

        assertThat(1234).isEqualTo(outcomeDto.getId());
        assertThat(10).isEqualTo(outcomeDto.getOutcome());
    }

    @Test
    public void sessionToSessionDtoMapTest() {

        var session = new Session();
        session.setChipValue(BigDecimal.TEN);
        session.setDateTime(Instant.now());
        session.setId(1234);
        session.setUserId(2345);

        var sessionDto = Mapper.map(session);

        assertThat(1234).isEqualTo(sessionDto.getId());
        assertThat(2345).isEqualTo(sessionDto.getUserId());
        assertThat(BigDecimal.TEN).isEqualTo(sessionDto.getChipValue());
    }

    @Test
    public void sessionDtoToSessionMapTest() {

        var sessionDto = new SessionDto();
        sessionDto.setChipValue(BigDecimal.TEN);
        sessionDto.setId(1234);
        sessionDto.setUserId(2345);

        var session = Mapper.map(sessionDto);

        assertThat(session.getId()).isEqualTo(1234);
        assertThat(session.getUserId()).isEqualTo(2345);
        assertThat(session.getChipValue()).isEqualTo(BigDecimal.TEN);
    }

    @Test
    public void sessionMetricsToSessionMetricsDtoTest() {

        var outcomeMap = RouletteOutcomeUtilities.getCompoundRouletteOutcome(12)
                .entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey, x -> 10L));


        var sessionMetrics = new SessionMetrics(outcomeMap,
                10L,
                BigDecimal.TEN,
                BigDecimal.ZERO,
                BigDecimal.TEN);


        var sessionMetricsDto = Mapper.map(sessionMetrics);

        assertThat(sessionMetricsDto.getCurrentProfit()).isEqualTo(BigDecimal.TEN);
        assertThat(sessionMetricsDto.getLeastProfit()).isEqualTo(BigDecimal.ZERO);
        assertThat(sessionMetricsDto.getTopProfit()).isEqualTo(BigDecimal.TEN);
        assertThat(sessionMetricsDto.getTotalRounds()).isEqualTo(10L);
        assertThat(sessionMetricsDto.getOutcomePercentageMap()).isEqualTo(sessionMetrics.getOutcomePercentageMap());
    }

    @Test
    public void adviceToAdviceDtoMapperTest() {

        var adviceMap = new TreeMap<>(RouletteOutcomeUtilities.getCompoundRouletteOutcome(12)
                .entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey, x -> BigDecimal.valueOf(Math.random() * 100))));

        var advice = new Advice(18, adviceMap);

        var adviceDto = Mapper.map(advice);

        assertThat(adviceDto.getId()).isEqualTo(advice.getId());
        assertThat(adviceDto.getAdviceMap()).isEqualTo(advice.getAdviceMap());
    }
}
