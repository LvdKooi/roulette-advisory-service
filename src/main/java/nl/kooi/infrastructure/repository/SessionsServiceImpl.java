package nl.kooi.infrastructure.repository;

import nl.kooi.infrastructure.model.Sessions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service


public class SessionsServiceImpl implements SessionsService {
    @Autowired
    private SessionsRepository sessionsRepository;

    public List<Sessions> findAll() {
        return sessionsRepository.findAll();
    }

    public Optional<Sessions> findById(int id) {
        return sessionsRepository.findById(id);
    }

    public Sessions save(Sessions session) {
        return sessionsRepository.save(session);
    }

    public void deleteById(int id) {
        sessionsRepository.deleteById(id);
    }

}
