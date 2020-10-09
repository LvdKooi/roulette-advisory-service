package nl.kooi.app.domain.service;

import nl.kooi.app.domain.Mapper;
import nl.kooi.app.domain.session.Session;
import nl.kooi.app.exception.NotFoundException;
import nl.kooi.app.persistence.entity.SessionEntity;
import nl.kooi.app.persistence.repository.SessionRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;

@SpringJUnitConfig(SessionService.class)
public class SessionServiceTest {

    @Autowired
    private SessionService sessionService;

    @MockBean
    private SessionRepository sessionRepository;

    private Instant instant;

    @BeforeEach
    public void initTestDependencies() {
        var instant = Instant.now();
        when(sessionRepository.save(Mapper.map(getPreSaveSession()))).thenReturn(getSessionEntity());
        when(sessionRepository.findById(12)).thenReturn(Optional.of(getSessionEntity()));
        when(sessionRepository.findByIdAndUserId(12, 1234)).thenReturn(Optional.of(getSessionEntity()));
    }

    @Test
    public void testSave() {
        assertSession(sessionService.save(getPreSaveSession()));
    }

    @Test
    public void testFindById() {
        assertSession(sessionService.findById(12));
    }

    @Test
    public void testFindByIdNonExisting() {
        Assertions.assertThrows(NotFoundException.class, () -> assertSession(sessionService.findById(11)));
    }

    @Test
    public void testFindByIdAndUserId() {
        assertSession(sessionService.findByIdAndUserId(12, 1234));
    }

    @Test
    public void testFindByIdAndUserIdNonExistingId() {
        Assertions.assertThrows(NotFoundException.class, () -> assertSession(sessionService.findByIdAndUserId(11, 1234)));
    }

    @Test
    public void testFindByIdAndUserIdNonExistingUserId() {
        Assertions.assertThrows(NotFoundException.class, () -> assertSession(sessionService.findByIdAndUserId(12, 2234)));
    }

    @Test
    public void testDeleteById() {
        String errormessage = null;
        try {
            sessionService.deleteById(12);
        } catch (NotFoundException e) {
            errormessage = e.getMessage();

        }

        assertNull(errormessage);
    }

    @Test
    public void testDeleteByIdNonExistingId() {
        Assertions.assertThrows(NotFoundException.class, () -> sessionService.deleteById(11));
    }

    private SessionEntity getSessionEntity() {
        var entity = new SessionEntity();
        entity.setChipValue(BigDecimal.TEN);
        entity.setUserId(1234);
        entity.setId(12);
        entity.setDateTime(instant);
        return entity;
    }

    private Session getPreSaveSession() {
        return new Session(1234, BigDecimal.TEN);
    }

    private void assertSession(Session session) {
        assertThat(session.getUserId()).isEqualTo(1234);
        assertThat(session.getId()).isEqualTo(12);
        assertThat(session.getChipValue()).isEqualTo(BigDecimal.TEN);
        assertThat(session.getDateTime()).isEqualTo(instant);
    }
}
