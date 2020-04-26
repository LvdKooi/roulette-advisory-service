package nl.kooi.app.domain.services;

import lombok.RequiredArgsConstructor;
import nl.kooi.app.domain.Mapper;
import nl.kooi.app.domain.session.Session;
import nl.kooi.app.exceptions.NotFoundException;
import nl.kooi.infrastructure.repository.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SessionService {
    @Autowired
    private SessionRepository sessionRepository;

    public Session save(Session session) {
        return Mapper.map(sessionRepository.save(Mapper.map(session)));
    }

    public Session findById(int id) {
        return Mapper.map(sessionRepository.findById(id).orElseThrow(() -> new NotFoundException("Session not found")));
    }

    public void deleteById(int id) {
        findById(id);
        sessionRepository.deleteById(id);
    }

    public Session findByIdAndUserId(int sessionId, int userId) {
        findById(sessionId);
        return Mapper.map(sessionRepository.findByIdAndUserId(sessionId, userId).orElseThrow(() -> new NotFoundException("User not found")));
    }


}
