package nl.kooi.app.domain.service;

import nl.kooi.app.domain.Mapper;
import nl.kooi.app.domain.outcome.Outcome;
import nl.kooi.infrastructure.entity.OutcomeEntity;
import nl.kooi.infrastructure.entity.SessionEntity;
import nl.kooi.infrastructure.repository.AdviceRepository;
import nl.kooi.infrastructure.repository.OutcomeRepository;
import nl.kooi.infrastructure.repository.SessionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

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

}
