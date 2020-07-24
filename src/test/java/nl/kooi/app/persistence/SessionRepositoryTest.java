package nl.kooi.app.persistence;

import nl.kooi.app.domain.Mapper;
import nl.kooi.app.domain.session.Session;
import nl.kooi.app.persistence.repository.SessionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class SessionRepositoryTest {

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
    public void findByIdAndUserIdTest() {
        assertThat(sessionRepository.findByIdAndUserId(sessionId, 1234)).isPresent();
    }

    @Test
    public void findByUnknownIdAndExistingUserIdTest() {
        assertThat(sessionRepository.findByIdAndUserId(9999, 1234)).isEmpty();
    }

    @Test
    public void findByExistingIdAndUnknownUserIdTest() {
        assertThat(sessionRepository.findByIdAndUserId(sessionId, 9999)).isEmpty();
    }

}
