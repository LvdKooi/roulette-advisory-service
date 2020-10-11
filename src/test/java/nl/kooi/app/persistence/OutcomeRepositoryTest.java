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
class OutcomeRepositoryTest {

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

        addAllAvailableRouletteNumbersToOutcomeRepoForSession(sessionId);

        outcomeRepository.save(Mapper.map(new Outcome(sessionId, 7)));
    }

    @Test
    public void findBySessionIdMethodsTest() {
        assertThat(outcomeRepository.findFirstBySessionIdOrderByIdDesc(sessionId).getOutcome()).isEqualTo(7);
        assertThat(outcomeRepository.findBySessionIdOrderByIdAsc(sessionId).size()).isEqualTo(38);
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
        assertThat(outcomeRepository.countByBlackAndSessionId(true, sessionId)).isEqualTo(18);
        assertThat(outcomeRepository.countByBlackAndSessionId(false, sessionId)).isEqualTo(20);
        assertThat(outcomeRepository.countByRedAndSessionId(true, sessionId)).isEqualTo(19);
        assertThat(outcomeRepository.countByRedAndSessionId(false, sessionId)).isEqualTo(19);
    }

    @Test
    public void halfCountersTest() {
        assertThat(outcomeRepository.countByFirstHalfAndSessionId(true, sessionId)).isEqualTo(19);
        assertThat(outcomeRepository.countByFirstHalfAndSessionId(false, sessionId)).isEqualTo(19);
        assertThat(outcomeRepository.countBySecondHalfAndSessionId(true, sessionId)).isEqualTo(18);
        assertThat(outcomeRepository.countBySecondHalfAndSessionId(false, sessionId)).isEqualTo(20);
    }

    @Test
    public void oddEvenCountersTest() {
        assertThat(outcomeRepository.countByOddAndSessionId(true, sessionId)).isEqualTo(19);
        assertThat(outcomeRepository.countByOddAndSessionId(false, sessionId)).isEqualTo(19);
        assertThat(outcomeRepository.countByEvenAndSessionId(true, sessionId)).isEqualTo(18);
        assertThat(outcomeRepository.countByEvenAndSessionId(false, sessionId)).isEqualTo(20);

    }

    @Test
    public void dozenCountersTest() {
        assertThat(outcomeRepository.countByFirstDozenAndSessionId(true, sessionId)).isEqualTo(13);
        assertThat(outcomeRepository.countByFirstDozenAndSessionId(false, sessionId)).isEqualTo(25);
        assertThat(outcomeRepository.countBySecondDozenAndSessionId(true, sessionId)).isEqualTo(12);
        assertThat(outcomeRepository.countBySecondDozenAndSessionId(false, sessionId)).isEqualTo(26);
        assertThat(outcomeRepository.countByThirdDozenAndSessionId(true, sessionId)).isEqualTo(12);
        assertThat(outcomeRepository.countByThirdDozenAndSessionId(false, sessionId)).isEqualTo(26);
    }

    @Test
    public void columnCountersTest() {
        assertThat(outcomeRepository.countByFirstColumnAndSessionId(true, sessionId)).isEqualTo(13);
        assertThat(outcomeRepository.countByFirstColumnAndSessionId(false, sessionId)).isEqualTo(25);
        assertThat(outcomeRepository.countBySecondColumnAndSessionId(true, sessionId)).isEqualTo(12);
        assertThat(outcomeRepository.countBySecondColumnAndSessionId(false, sessionId)).isEqualTo(26);
        assertThat(outcomeRepository.countByThirdColumnAndSessionId(true, sessionId)).isEqualTo(12);
        assertThat(outcomeRepository.countByThirdColumnAndSessionId(false, sessionId)).isEqualTo(26);
    }

    @Test
    public void zeroCountersTest() {
        assertThat(outcomeRepository.countByZeroAndSessionId(true, sessionId)).isEqualTo(1);
        assertThat(outcomeRepository.countByZeroAndSessionId(false, sessionId)).isEqualTo(37);
    }

    @Test
    public void secondSessionForUserDoesNotAffectCountersOfFirstSessionTest() {

        var session = new Session();
        session.setChipValue(BigDecimal.TEN);
        session.setUserId(1234);

        var sessionId2 = sessionRepository.save(Mapper.map(session)).getId();

        addAllAvailableRouletteNumbersToOutcomeRepoForSession(sessionId2);

        assertThat(outcomeRepository.countByFirstColumnAndSessionId(true, sessionId)).isEqualTo(13);
        assertThat(outcomeRepository.countByFirstColumnAndSessionId(false, sessionId)).isEqualTo(25);
        assertThat(outcomeRepository.countBySecondColumnAndSessionId(true, sessionId)).isEqualTo(12);
        assertThat(outcomeRepository.countBySecondColumnAndSessionId(false, sessionId)).isEqualTo(26);
        assertThat(outcomeRepository.countByThirdColumnAndSessionId(true, sessionId)).isEqualTo(12);
        assertThat(outcomeRepository.countByThirdColumnAndSessionId(false, sessionId)).isEqualTo(26);
    }

    private void addAllAvailableRouletteNumbersToOutcomeRepoForSession(int sessionId) {
        IntStream.rangeClosed(0, 36)
                .mapToObj(number -> Mapper.map(new Outcome(sessionId, number)))
                .forEach(outcomeRepository::save);
    }

}
