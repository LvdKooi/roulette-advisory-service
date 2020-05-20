package nl.kooi.app.api.dto;

import lombok.var;
import nl.kooi.app.domain.advice.Advice;
import nl.kooi.app.domain.metric.SessionMetrics;
import nl.kooi.app.domain.outcome.Outcome;
import nl.kooi.app.domain.rouletteoutcome.RouletteOutcomeUtilities;
import nl.kooi.app.domain.session.Session;
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

        assertThat("Id of OutcomeDto doesn't match the one of Outcome.", 1234, equalTo(outcomeDto.getId()));
        assertThat("Outcome of OutcomeDto doesn't match the one of Outcome.", 10, equalTo(outcomeDto.getOutcome()));
    }

    @Test
    public void sessionToSessionDtoMapTest() {

        var session = new Session();
        session.setChipValue(BigDecimal.TEN);
        session.setDateTime(LocalDateTime.now());
        session.setId(1234);
        session.setUserId(2345);

        var sessionDto = Mapper.map(session);

        assertThat("Id of SessionDto doesn't match the one of Session.", 1234, equalTo(sessionDto.getId()));
        assertThat("UserId of SessionDto doesn't match the one of Session.", 2345, equalTo(sessionDto.getUserId()));
        assertThat("ChipValue of SessionDto doesn't match the one of Session.", BigDecimal.TEN, equalTo(sessionDto.getChipValue()));
    }

    @Test
    public void sessionDtoToSessionMapTest() {

        var sessionDto = new SessionDto();
        sessionDto.setChipValue(BigDecimal.TEN);
        sessionDto.setId(1234);
        sessionDto.setUserId(2345);

        var session = Mapper.map(sessionDto);

        assertThat("Id of Session doesn't match the one of SessionDto.", 1234, equalTo(session.getId()));
        assertThat("UserId of Session doesn't match the one of SessionDto.", 2345, equalTo(session.getUserId()));
        assertThat("ChipValue of Session doesn't match the one of SessionDto.", BigDecimal.TEN, equalTo(session.getChipValue()));
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

        assertThat("CurrentProfit of SessionMetricsDto doesn't match the one of SessionMetrics.", BigDecimal.TEN, equalTo(sessionMetricsDto.getCurrentProfit()));
        assertThat("LeastProfit of SessionMetricsDto doesn't match the one of SessionMetrics.", BigDecimal.ZERO, equalTo(sessionMetricsDto.getLeastProfit()));
        assertThat("TopProfit of SessionMetricsDto doesn't match the one of SessionMetrics.", BigDecimal.TEN, equalTo(sessionMetricsDto.getTopProfit()));
        assertThat("TotalRounds of SessionMetricsDto doesn't match the one of SessionMetrics.", 10L, equalTo(sessionMetricsDto.getTotalRounds()));
        assertThat("OutcomePercentageMap of SessionMetricsDto doesn't match the one of SessionMetrics.", sessionMetrics.getOutcomePercentageMap(), equalTo(sessionMetricsDto.getOutcomePercentageMap()));
    }

    @Test
    public void adviceToAdviceDtoMapperTest() {

        var adviceMap = new TreeMap<>(RouletteOutcomeUtilities.getCompoundRouletteOutcome(12)
                .entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey, x -> BigDecimal.valueOf(Math.random() * 100))));

        var advice = new Advice(18, adviceMap);

        var adviceDto = Mapper.map(advice);

        assertThat("Id of adviceDto doesn't match the one of Advice.", advice.getId(), equalTo(adviceDto.getId()));
        assertThat("AdviceMap of adviceDto doesn't match the one of Advice.", advice.getAdviceMap(), equalTo(adviceDto.getAdviceMap()));
    }
}
