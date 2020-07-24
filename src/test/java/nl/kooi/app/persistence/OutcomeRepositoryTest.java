package nl.kooi.app.persistence;

import nl.kooi.app.domain.Mapper;
import nl.kooi.app.domain.outcome.Outcome;
import nl.kooi.app.domain.session.Session;
import nl.kooi.app.persistence.repository.OutcomeRepository;
import nl.kooi.app.persistence.repository.SessionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class OutcomeRepositoryTest {

    @Autowired
    private OutcomeRepository outcomeRepository;

    @Autowired
    private SessionRepository sessionRepository;

    int sessionId;
    int sessionIdForCounterTests;

    @BeforeEach
    public void initTestDependencies() {

        sessionRepository.deleteAll();

        var session = new Session();
        session.setChipValue(BigDecimal.TEN);
        session.setUserId(1234);

        sessionId = sessionRepository.save(Mapper.map(session)).getId();

        sessionIdForCounterTests = sessionRepository.save(Mapper.map(session)).getId();

        IntStream.rangeClosed(1, 36)
                .mapToObj(number -> Mapper.map(new Outcome(sessionIdForCounterTests, number)))
                .forEach(outcomeRepository::save);

        outcomeRepository.save(Mapper.map(new Outcome(sessionIdForCounterTests, 1)));

    }

    @Test
    public void findBySessionIdMethodsTest() {
        var outcome = new Outcome(sessionId, 1, BigDecimal.TEN);
        outcomeRepository.save(Mapper.map(outcome));

        outcome = new Outcome(sessionId, 12, BigDecimal.TEN);
        outcomeRepository.save(Mapper.map(outcome));

        assertThat(outcomeRepository.findFirstBySessionIdOrderByIdDesc(sessionId).getOutcome()).isEqualTo(12);
        assertThat(outcomeRepository.findBySessionIdOrderByIdAsc(sessionId).size()).isEqualTo(2);
    }

    @Test
    public void profitMethodsTest() {

        var outcome = new Outcome(sessionId, 1, BigDecimal.valueOf(100));

        outcomeRepository.save(Mapper.map(outcome));

        outcome = new Outcome(sessionId, 12, BigDecimal.valueOf(-10));

        outcomeRepository.save(Mapper.map(outcome));

        assertThat(outcomeRepository.getLeastProfitAmount(sessionId)).isEqualTo(BigDecimal.valueOf(-10));
        assertThat(outcomeRepository.getHighestProfitAmount(sessionId)).isEqualTo(BigDecimal.valueOf(100));
    }

    @Test
    public void redBlackCountersTest() {
        assertThat(outcomeRepository.countByBlackAndSessionId(true, sessionIdForCounterTests)).isEqualTo(18);
        assertThat(outcomeRepository.countByBlackAndSessionId(false, sessionIdForCounterTests)).isEqualTo(19);
        assertThat(outcomeRepository.countByRedAndSessionId(true, sessionIdForCounterTests)).isEqualTo(19);
        assertThat(outcomeRepository.countByRedAndSessionId(false, sessionIdForCounterTests)).isEqualTo(18);
    }

    @Test
    public void halfCountersTest() {
        assertThat(outcomeRepository.countByFirstHalfAndSessionId(true, sessionIdForCounterTests)).isEqualTo(19);
        assertThat(outcomeRepository.countByFirstHalfAndSessionId(false, sessionIdForCounterTests)).isEqualTo(18);
        assertThat(outcomeRepository.countBySecondHalfAndSessionId(true, sessionIdForCounterTests)).isEqualTo(18);
        assertThat(outcomeRepository.countBySecondHalfAndSessionId(false, sessionIdForCounterTests)).isEqualTo(19);


    }

    @Test
    public void oddEvenCountersTest() {
        assertThat(outcomeRepository.countByOddAndSessionId(true, sessionIdForCounterTests)).isEqualTo(19);
        assertThat(outcomeRepository.countByOddAndSessionId(false, sessionIdForCounterTests)).isEqualTo(18);
        assertThat(outcomeRepository.countByEvenAndSessionId(true, sessionIdForCounterTests)).isEqualTo(18);
        assertThat(outcomeRepository.countByEvenAndSessionId(false, sessionIdForCounterTests)).isEqualTo(19);

    }

    @Test
    public void dozenCountersTest() {
        assertThat(outcomeRepository.countByFirstDozenAndSessionId(true, sessionIdForCounterTests)).isEqualTo(13);
        assertThat(outcomeRepository.countByFirstDozenAndSessionId(false, sessionIdForCounterTests)).isEqualTo(24);
        assertThat(outcomeRepository.countBySecondDozenAndSessionId(true, sessionIdForCounterTests)).isEqualTo(12);
        assertThat(outcomeRepository.countBySecondDozenAndSessionId(false, sessionIdForCounterTests)).isEqualTo(25);
        assertThat(outcomeRepository.countByThirdDozenAndSessionId(true, sessionIdForCounterTests)).isEqualTo(12);
        assertThat(outcomeRepository.countByThirdDozenAndSessionId(false, sessionIdForCounterTests)).isEqualTo(25);
    }

    @Test
    public void columnCountersTest() {
        assertThat(outcomeRepository.countByFirstColumnAndSessionId(true, sessionIdForCounterTests)).isEqualTo(13);
        assertThat(outcomeRepository.countByFirstColumnAndSessionId(false, sessionIdForCounterTests)).isEqualTo(24);
        assertThat(outcomeRepository.countBySecondColumnAndSessionId(true, sessionIdForCounterTests)).isEqualTo(12);
        assertThat(outcomeRepository.countBySecondColumnAndSessionId(false, sessionIdForCounterTests)).isEqualTo(25);
        assertThat(outcomeRepository.countByThirdColumnAndSessionId(true, sessionIdForCounterTests)).isEqualTo(12);
        assertThat(outcomeRepository.countByThirdColumnAndSessionId(false, sessionIdForCounterTests)).isEqualTo(25);
    }

}
