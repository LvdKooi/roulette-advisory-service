package nl.kooi.app.domain.service;

import nl.kooi.app.domain.Mapper;
import nl.kooi.app.domain.advice.Advice;
import nl.kooi.app.domain.metric.SessionMetrics;
import nl.kooi.app.domain.outcome.Outcome;
import nl.kooi.app.domain.rouletteoutcome.RouletteOutcome;
import nl.kooi.app.persistence.entity.AdviceEntity;
import nl.kooi.app.persistence.entity.OutcomeEntity;
import nl.kooi.app.persistence.entity.SessionEntity;
import nl.kooi.app.persistence.repository.AdviceRepository;
import nl.kooi.app.persistence.repository.OutcomeRepository;
import nl.kooi.app.persistence.repository.SessionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;
import java.util.function.Function;

import static nl.kooi.app.domain.rouletteoutcome.RouletteOutcome.ZERO;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringJUnitConfig(OutcomeAdviceService.class)
public class OutcomeAdviceServiceTest {

    @Autowired
    private OutcomeAdviceService outcomeAdviceService;

    @MockBean
    private OutcomeRepository outcomeRepository;

    @MockBean
    private AdviceRepository adviceRepository;

    @MockBean
    private SessionRepository sessionRepository;

    private Instant instant;

    @BeforeEach
    public void initTestDependencies() {
        var instant = Instant.now();
        when(sessionRepository.findByIdAndUserId(12, 1234)).thenReturn(Optional.of(getSessionEntity()));
        when(outcomeRepository.findBySessionIdOrderByIdAsc(12)).thenReturn(getOutcomeEntities());
        when(outcomeRepository.save(Mapper.map(new Outcome(12, 5)))).thenReturn(getOutcomeEntities().get(0));
        when(outcomeRepository.countByBlackAndSessionId(true, 12)).thenReturn(1L);
        when(outcomeRepository.countByRedAndSessionId(true, 12)).thenReturn(2L);
        when(outcomeRepository.countByOddAndSessionId(true, 12)).thenReturn(3L);
        when(outcomeRepository.countByEvenAndSessionId(true, 12)).thenReturn(4L);
        when(outcomeRepository.countByFirstHalfAndSessionId(true, 12)).thenReturn(5L);
        when(outcomeRepository.countBySecondHalfAndSessionId(true, 12)).thenReturn(6L);
        when(outcomeRepository.countByFirstColumnAndSessionId(true, 12)).thenReturn(7L);
        when(outcomeRepository.countBySecondColumnAndSessionId(true, 12)).thenReturn(8L);
        when(outcomeRepository.countByThirdColumnAndSessionId(true, 12)).thenReturn(9L);
        when(outcomeRepository.countByFirstDozenAndSessionId(true, 12)).thenReturn(10L);
        when(outcomeRepository.countBySecondDozenAndSessionId(true, 12)).thenReturn(11L);
        when(outcomeRepository.countByThirdDozenAndSessionId(true, 12)).thenReturn(12L);
        when(outcomeRepository.countByZeroAndSessionId(true, 12)).thenReturn(0L);
        when(outcomeRepository.countBySessionId(12)).thenReturn(91L);
        when(outcomeRepository.getLeastProfitAmount(12)).thenReturn(BigDecimal.valueOf(-20));
        when(outcomeRepository.getHighestProfitAmount(12)).thenReturn(BigDecimal.TEN);
        when(outcomeRepository.findFirstBySessionIdOrderByIdDesc(12)).thenReturn(getOutcomeEntityWithProfit());
        when(adviceRepository.findFirstBySessionIdOrderByIdDesc(12)).thenReturn(getAdviceEntity());
    }

    @Test
    public void saveOutcomeAndAdviseTest() {
        outcomeAdviceService.saveOutcomeAndAdvise(1234, 12, 5);
        verify(outcomeRepository).save(argThat(entity -> entity != null
                && entity.getSession().getId() == 12
                && entity.getOutcome() == 5));
        verify(adviceRepository).save(argThat(entity -> entity != null
                && entity.getSession().equals(getSessionEntity())));
    }

    @Test
    public void findLastAdviceTest() {
        assertThat(outcomeAdviceService.findLastAdvice(12)).isEqualTo(getExpectedAdvice());

    }

    @Test
    public void getSessionMetricsTest() {
        var totalRounds = 91L;
        var currentProfit = BigDecimal.valueOf(100);
        var leastProfit = BigDecimal.valueOf(-20);
        var topProfit = BigDecimal.TEN;

        var expectedSessionMetrics = new SessionMetrics(getExpectedOutcomeCounter(), totalRounds, currentProfit, leastProfit, topProfit);

        assertThat(outcomeAdviceService.getSessionsMetrics(12)).isEqualTo(expectedSessionMetrics);
    }


    private SessionEntity getSessionEntity() {
        var entity = new SessionEntity();
        entity.setChipValue(BigDecimal.TEN);
        entity.setUserId(1234);
        entity.setId(12);
        entity.setDateTime(instant);
        return entity;
    }

    private List<OutcomeEntity> getOutcomeEntities() {
        var outcome = new Outcome(12, 5);
        return List.of(Mapper.map(outcome), Mapper.map(outcome), Mapper.map(outcome));
    }

    private OutcomeEntity getOutcomeEntityWithProfit() {
        var outcome = new OutcomeEntity();
        outcome.setSession(getSessionEntity());
        outcome.setTotalProfit(BigDecimal.valueOf(100));
        return outcome;
    }

    private Advice getExpectedAdvice() {
        var adviceMap = new TreeMap<>(getCounterMap(BigDecimal::valueOf));
        adviceMap.remove(ZERO);
        return new Advice(adviceMap);

    }

    private AdviceEntity getAdviceEntity() {
        return Mapper.map(getExpectedAdvice());
    }

    private Map<RouletteOutcome, Long> getExpectedOutcomeCounter() {
        return getCounterMap(Long::valueOf);
    }

    private <T> Map<RouletteOutcome, T> getCounterMap(Function<Integer, T> function) {

        var outcomeCounterMap = new TreeMap<RouletteOutcome, T>();

        var i = 0;

        for (RouletteOutcome outcome : RouletteOutcome.values()) {
            var arg = function.apply(i++);
            outcomeCounterMap.put(outcome, arg);

        }

        return outcomeCounterMap;

    }
}


