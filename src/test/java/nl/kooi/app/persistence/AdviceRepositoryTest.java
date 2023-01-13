package nl.kooi.app.persistence;

import nl.kooi.app.domain.Mapper;
import nl.kooi.app.domain.outcome.Outcome;
import nl.kooi.app.domain.session.Session;
import nl.kooi.app.persistence.entity.AdviceEntity;
import nl.kooi.app.persistence.repository.AdviceRepository;
import nl.kooi.app.persistence.repository.OutcomeRepository;
import nl.kooi.app.persistence.repository.SessionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

class AdviceRepositoryTest extends DataJpaTestContainersBase {

    @Autowired
    private AdviceRepository adviceRepository;

    @Autowired
    private OutcomeRepository outcomeRepository;

    @Autowired
    private SessionRepository sessionRepository;

    int sessionId;

    @BeforeEach
    public void initTestDependencies() {

        sessionRepository.deleteAll();

        var session = new Session();
        session.setChipValue(BigDecimal.TEN);
        session.setUserId(1234);

        sessionId = sessionRepository.save(Mapper.map(session)).getId();

    }

    @Test
    public void findFirstBySessionIdOrderByIdDescTest() {
        var outcome = outcomeRepository.save(Mapper.map(new Outcome(sessionId, 7)));
        var session = sessionRepository.getOne(sessionId);

        var advice = new AdviceEntity();
        advice.setCausingOutcome(outcome);
        advice.setSession(session);
        adviceRepository.save(advice);

        outcome = outcomeRepository.save(Mapper.map(new Outcome(sessionId, 14)));

        advice = new AdviceEntity();
        advice.setCausingOutcome(outcome);
        advice.setSession(session);
        var lastAdvice = adviceRepository.save(advice);

        assertThat(adviceRepository.findFirstBySessionIdOrderByIdDesc(sessionId)).isEqualTo(lastAdvice);

    }

}
